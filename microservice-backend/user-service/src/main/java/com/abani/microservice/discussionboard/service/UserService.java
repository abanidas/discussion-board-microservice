package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.User;
import com.mongodb.MongoWriteException;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User save(User user) throws MongoWriteException;
    Optional<User> findByEmail(String email);
    List<User> findAll();
    Optional<User> findByUsername(String username);
    boolean deleteByUsername(String username);
    boolean update(User user);
}
