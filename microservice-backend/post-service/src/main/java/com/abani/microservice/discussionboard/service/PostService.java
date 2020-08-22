package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Post;

import java.util.List;
import java.util.Optional;

public interface PostService {

    Post save(Post post);
    Optional<Post> getPostById(String id);
    List<Post> getAllPosts();
    List<Post> getPostsByIds(List<String> ids);
    List<Post> getPostsByUsername(String username);
    boolean update(Post post);
    boolean deletePostById(String id);
}
