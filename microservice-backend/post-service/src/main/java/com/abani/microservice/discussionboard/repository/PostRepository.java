package com.abani.microservice.discussionboard.repository;

import com.abani.microservice.discussionboard.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends MongoRepository<Post, String> {

    @Query(value = "{'username' : ?0}")
    List<Post> findByUsername(String username);
}
