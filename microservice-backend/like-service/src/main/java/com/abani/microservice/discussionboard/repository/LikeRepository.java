package com.abani.microservice.discussionboard.repository;

import com.abani.microservice.discussionboard.model.Like;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends MongoRepository<Like, String> {
    
    @Query(value = "{'postType' : ?0, 'postId' : ?1}")
    List<Like> findByPostTypeAndId(String postType, String postId);
}
