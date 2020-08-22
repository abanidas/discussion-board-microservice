package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Like;

import java.util.List;
import java.util.Optional;

public interface LikeService {

    Like save(Like like);
    Optional<Like> getLikeById(String id);
    List<Like> getAllLikes();
    List<Like> getLikesByIds(List<String> ids);
    List<Like> getLikesByPostTypeAndId(String postType, String postId);
    boolean update(Like like);
    boolean deleteLikeById(String id);
    boolean deleteLikesByIds(List<String> ids);
    boolean deleteLikesByPostTypeAndId(String postType, String postId);
}
