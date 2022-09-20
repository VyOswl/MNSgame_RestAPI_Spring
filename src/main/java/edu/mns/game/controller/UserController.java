package edu.mns.game.controller;

import edu.mns.game.dao.RoleDao;
import edu.mns.game.dao.UserDao;
import edu.mns.game.model.Role;
import edu.mns.game.model.User;
import edu.mns.game.security.JwtUtils;
import edu.mns.game.security.UserDetailsMnsGame;
import edu.mns.game.security.UserDetailsServiceMnsGame;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
//???
@RestController
//indique qu'il s'agit d'un controller
@AllArgsConstructor
public class UserController {
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceMnsGame userDetailsServiceMnsGame;

    @Autowired
    public UserController(UserDao userDao){
        this.userDao = userDao;
    }


    /**
     *
     * @param user
     * @return
     * @throws Exception
     */
    @PostMapping("/connection")
    public Map<String,String> connexion(@RequestBody User user) throws Exception{
        //récup de l'utilisateur en Json
        // vérifier que le personne existe dans la bdd
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            user.getEmail(),
                            user.getPassword()
                    )
            );
        }catch (BadCredentialsException e){
            Map<String,String> retour = new HashMap<>();
            retour.put("erreur", "mauvais login/ mot de passe");
            return retour;
        }

        UserDetailsMnsGame userDetails = userDetailsServiceMnsGame.loadUserByUsername(user.getEmail());
        Map<String,String> retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));
        return retour;
    }

    @PostMapping("/inscription")
    public Map<String,String> inscription(@RequestBody @Valid User user) throws Exception {
        user.setPassword((encoder.encode(user.getPassword())));
        Role defaultRole = roleDao.findByName("ROLE_USER");
        user.addDefaultRole(defaultRole);
        userDao.save(user);
        UserDetailsMnsGame userDetails = userDetailsServiceMnsGame.loadUserByUsername(user.getEmail());

        Map<String,String> retour = new HashMap<>();
        retour.put("token", jwtUtils.generateToken(userDetails));
        return retour;
    }

    @GetMapping("/admin/list-user")
    public List<User> listeUser(){
        return this.userDao.findAll();
    }

    @GetMapping("/creator/user/{id}")
    public ResponseEntity<User> user(@PathVariable Integer id) {
        //---PathVariable : la variable qui suit prend sa valeur dans l'URL---//
        Optional<User> user = this.userDao.findById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(user.get());
        } else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/creator/user/by-lastname/{lastname}")
    public User findByLastname( @PathVariable String lastname){
        return this.userDao.findByLastname(lastname).orElse(null);
    }

    @GetMapping("/creator/user/by-firstname/{firstname}")
    public User findByFirstname( @PathVariable String firstname){
        return this.userDao.findByFirstname(firstname).orElse(null);
    }

    @GetMapping("/creator/list-user/by-scenario/{scenarioId}")
    public List<User> findAllByScenario(@PathVariable int scenarioId){
        return this.userDao.findAllByScenario(scenarioId).orElse(null);
    }

    @PostMapping("/admin/new-user")
    public ResponseEntity createUser(@RequestBody User user) {
        this.userDao.saveAndFlush(user);
        //save and flush récupère l'id s'il a été auto incrémenté
        return ResponseEntity
                .created(URI.create("/user/" + user.getId()))
                .build();
    }

    @PostMapping("/admin/edit/user")
    public ResponseEntity<User> createUser(
            @RequestBody User user,
            @RequestHeader("Authorization") String jwt){

        String token = jwt.substring(7);
        int idConnectedUser = (int) jwtUtils.getTokenBody(token).get("id");
        String droits = (String) jwtUtils.getTokenBody(token).get("droits");

        if(droits.contains("ROLE_ADMIN") || idConnectedUser == user.getId()){
            System.out.println("update user, ");
            Optional<User> userInBDD = userDao.findById(user.getId());

            if(userInBDD.isPresent()){
                userInBDD.get().setFirstname(user.getFirstname());
                userInBDD.get().setLastname(user.getLastname());
                //userInBDD.get().setEmail(user.getEmail());

                this.userDao.save(userInBDD.get());

                return ResponseEntity.ok(userInBDD.get());
            }
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

        @GetMapping("/disconnection")
        public ResponseEntity<String> disconnection(@RequestHeader("Authorization") String jwt){
            String token = jwt.substring(7);
            int idConnectedUser = (int) jwtUtils.getTokenBody(token).get("id");
            Optional<User> optionalUser = userDao.findById((idConnectedUser));
            if(optionalUser.isPresent()) {
                optionalUser.get().setNumberToken(optionalUser.get().getNumberToken() + 1);
                userDao.save(optionalUser.get());
                return ResponseEntity.ok().build();
            }
            return ResponseEntity.noContent().build();
        }

    @DeleteMapping("/admin/delete-user/{id}")
    public ResponseEntity<User> deleteUtilisateur(@PathVariable int id){

        Optional<User> userToDelete = userDao.findById(id);
        if(userToDelete.isPresent()){
            this.userDao.deleteById(id);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.noContent().build();
        }

    }

}