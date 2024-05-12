package com.lowleveldesign.crms.Services.auth;

import com.lowleveldesign.crms.Models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private String email;
    private String password;
    Collection<? extends GrantedAuthority> authorities;
    public UserDetailsImpl(User byUsername) {
        this.email = byUsername.getEmail();
        this.password= byUsername.getPassword();
        List<GrantedAuthority> auths = new ArrayList<>();

        /**
         * To Do - Can Use Role->Authorities mapping defined in Constants class to get more
         * flexibility for authorization based on multiple defined authority for different
         * level of Roles. Just use that mapping and populate the list of authority*/
        auths.add(new SimpleGrantedAuthority(byUsername.getRoles().getRole().toString()));

        this.authorities = auths;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
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
