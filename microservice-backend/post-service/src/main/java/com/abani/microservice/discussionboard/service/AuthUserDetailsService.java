package com.abani.microservice.discussionboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserService authUserService;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Map<Object, Object> user = authUserService.findAuthUserByUsername(username);

        if(user == null || user.isEmpty()) {
            ResponseEntity<Object> responseEntity = restTemplate.getForObject("http://user-service/users/" + username + "/notify-login", ResponseEntity.class);
            user = authUserService.findAuthUserByUsername(username);
        }
        if(user == null || user.isEmpty()) {
            throw new UsernameNotFoundException("User does not exist!");
        }

        AuthUserDetails userDetails = new AuthUserDetails(user);
        return userDetails;
    }
}
