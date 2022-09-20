package edu.mns.game.security;

import edu.mns.game.dao.CreatorDao;
import edu.mns.game.dao.UserDao;
import edu.mns.game.model.Role;
import edu.mns.game.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;


public class UserDetailsMnsGame implements UserDetails {

    private User user;
    private boolean creator;

    public User getUser() {
        return user;
    }

    public UserDetailsMnsGame(User user/*, boolean creator*/) {
        this.user = user;
        this.creator=creator;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<SimpleGrantedAuthority> listeAuthority = new ArrayList<>();
        /*if(creator) {
            listeAuthority.add(new SimpleGrantedAuthority("creator"));
        }*/
        for(Role role : this.user.getRoleList()){
            listeAuthority.add(new SimpleGrantedAuthority(role.getName()));
        }
        return listeAuthority;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
         return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}


