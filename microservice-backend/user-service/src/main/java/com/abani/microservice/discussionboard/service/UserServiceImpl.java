package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.User;
import com.abani.microservice.discussionboard.repository.UserRepository;
import com.mongodb.MongoWriteException;
import com.mongodb.ServerAddress;
import com.mongodb.WriteError;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(User user) throws MongoWriteException {
        if(userRepository.existsById(user.getUsername())){
            throw new MongoWriteException(
                    new WriteError(1, "Already Exist", new BsonDocument()),
                    new ServerAddress());
        }
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findById(username);
    }

    @Override
    public boolean deleteByUsername(String username) {
        if(userRepository.existsById(username)){
            userRepository.deleteById(username);
        }
        else {
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {
        return userRepository.existsById(user.getUsername()) && userRepository.save(user) != null;
    }
}
