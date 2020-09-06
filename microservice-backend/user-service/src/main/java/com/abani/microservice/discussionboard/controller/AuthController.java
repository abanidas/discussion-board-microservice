package com.abani.microservice.discussionboard.controller;

import com.abani.microservice.discussionboard.model.User;
import com.abani.microservice.discussionboard.service.UserService;
import com.abani.microservice.discussionboard.util.JwtUtil;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class AuthController {


    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/users/signup")
    public ResponseEntity<String> addUser(@RequestBody User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            userService.save(user);

            Map<String, String> authUser = new HashMap<>();
            authUser.put("username", user.getUsername());
            authUser.put("password", user.getPassword());
            redisTemplate.opsForHash().putAll(user.getUsername(), authUser);

        } catch (MongoWriteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Create Successfully");
    }

    @PostMapping("/users/signin")
    public ResponseEntity<String> signIn(@RequestBody Map<String, Object> user) {

        if(!user.containsKey("username") || !user.containsKey("password")){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Username/password");
        }
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(user.get("username"), user.get("password")));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Invalid Username/password");
        }
        return ResponseEntity.ok(jwtUtil.generateToken(user.get("username").toString()));
        //return restTemplate.getForObject("http://user-service/users/" + user.get("username") + "/notify-login", ResponseEntity.class);
    }

}
