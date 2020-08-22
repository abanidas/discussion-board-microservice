package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentService {

    Comment save(Comment comment);
    Optional<Comment> getCommentById(String id);
    List<Comment> getAllComments();
    List<Comment> getCommentsByIds(List<String> ids);
    List<Comment> getCommentsByUsername(String username);
    List<Comment> getCommentsByPostId(String postId);
    boolean update(Comment comment);
    boolean deleteCommentById(String id);
    boolean deleteCommentsByIds(List<String> ids);

    boolean deleteCommentsByPostId(String postId);
}
