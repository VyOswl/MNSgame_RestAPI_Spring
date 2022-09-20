package edu.mns.game.controller;

import edu.mns.game.dao.CreatorDao;
import edu.mns.game.model.Creator;
import edu.mns.game.security.JwtUtils;
import edu.mns.game.security.UserDetailsServiceMnsGame;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin //permet d'autoriser les requêtes vers "tout le monde"/ d'origines différentes, du navigateur vers le serveur, postman...
@RestController
@AllArgsConstructor
public class CreatorController {
    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserDetailsServiceMnsGame userDetailsServiceMnsGame;

    @Autowired
    public CreatorController(CreatorDao creatorDao){
        this.creatorDao = creatorDao;
    }

    @GetMapping("/admin/list-creator")
    public List<Creator> listeCreator(){
        return this.creatorDao.findAll();
    }

    @GetMapping("/creator/{id}")
    public Creator creator(@PathVariable Integer id){

        return this.creatorDao.findById(id).orElse(null);
    }
}
