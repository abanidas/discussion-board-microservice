package com.abani.microservice.discussionboard.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

public class AuthUserDetails implements UserDetails {

    private Map<Object, Object> authUser;

    public AuthUserDetails(Map<Object, Object> authUser) {
        this.authUser = authUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new HashSet<GrantedAuthority>();
    }

    @Override
    public String getPassword() {
        return authUser.get("password").toString();
    }

    @Override
    public String getUsername() {
        return authUser.get("username").toString();
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
