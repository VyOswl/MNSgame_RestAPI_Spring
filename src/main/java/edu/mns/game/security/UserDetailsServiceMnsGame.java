package edu.mns.game.security;

import edu.mns.game.dao.CreatorDao;
import edu.mns.game.dao.UserDao;
import edu.mns.game.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceMnsGame implements UserDetailsService {

    private UserDao userDao;

    @Autowired
    private CreatorDao creatorDao;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    UserDetailsServiceMnsGame(UserDao userDao){
        this.userDao = userDao;
    }



    @Override
    public UserDetailsMnsGame loadUserByUsername(String email) throws UsernameNotFoundException{
        System.out.println("loadUserByEmail in userDetailsServiceMns... EMAIL : "+email);
        User user = userDao
                .findByEmailWithRoles(email)
                .orElseThrow(() -> new UsernameNotFoundException("Mauvais pseudo / mot de passe"));

        /*Boolean creator = creatorDao.findById(user.getId()).isPresent();
        UserDetailsMnsGame userDetailsMnsGame = new UserDetailsMnsGame(user,creator);
        System.out.println(jwtUtils.generateToken(userDetailsMnsGame));
        System.out.println(creator);*/
        return new UserDetailsMnsGame(user);
    }
}
