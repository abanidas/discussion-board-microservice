package com.abani.microservice.discussionboard.repository;

import com.abani.microservice.discussionboard.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String> {

    @Query(value = "{'postId' : ?0}")
    List<Comment> findByPostId(String postId);

    @Query(value = "{'username' : ?0}")
    List<Comment> findByUsername(String username);
}
