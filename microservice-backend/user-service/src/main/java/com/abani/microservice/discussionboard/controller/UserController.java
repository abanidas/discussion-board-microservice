package com.abani.microservice.discussionboard.controller;

import com.abani.microservice.discussionboard.model.User;
import com.abani.microservice.discussionboard.repository.UserRepository;
import com.abani.microservice.discussionboard.service.UserService;
import com.mongodb.MongoException;
import com.mongodb.MongoWriteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        try {
            userService.save(user);
        } catch (MongoWriteException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestParam(name = "email", required = false) String email){

        if (email != null) {
            List<User> data = new ArrayList<>();
            Optional<User> result = userService.findByEmail(email);
            if (result.isPresent()) {
                data.add(result.get());
            }
            return data;
        }
        return userService.findAll();
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        return ResponseEntity.ok(userService.findByUsername(username).orElse(null));
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity deleteUser(@PathVariable("username") String username){
        if(!userService.deleteByUsername(username)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User does not exist!");
        }
        return ResponseEntity.ok("User Deleted Successfully");
    }

    @PutMapping("/users/{username}/posts/{id}")
    public ResponseEntity<String> addPost(@PathVariable("username") String username, @PathVariable("id") String id){
        User user = userService.findByUsername(username).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        if(user.getPostIds() == null) {
            user.setPostIds(new ArrayList<>());
        }
        user.getPostIds().add(id);
        return userService.update(user) ? ResponseEntity.ok("Updated Successfully") : ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{username}")
    public ResponseEntity<String> updateUser(@PathVariable("username") String username, @RequestBody User user){
        User existing = userService.findByUsername(username).get();
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("User doesn't exist!");
        }

        if (user.getFirstName() != null) existing.setFirstName(user.getFirstName());
        if (user.getLastName() != null) existing.setLastName(user.getLastName());
        if (user.getImgUrl() != null) existing.setImgUrl(user.getImgUrl());

        return userService.update(existing)
                ? ResponseEntity.ok("Updated Successfully")
                : ResponseEntity.status(HttpStatus.NO_CONTENT).body("Couldn't Update!");
    }
}
