package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Comment;
import com.abani.microservice.discussionboard.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }

    @Override
    public Optional<Comment> getCommentById(String id) {
        return commentRepository.findById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    @Override
    public List<Comment> getCommentsByIds(List<String> ids) {
        return (List<Comment>) commentRepository.findAllById(ids);
    }

    @Override
    public List<Comment> getCommentsByUsername(String username) {
        return commentRepository.findByUsername(username);
    }

    @Override
    public List<Comment> getCommentsByPostId(String postId) {
        return commentRepository.findByPostId(postId);
    }

    @Override
    public boolean update(Comment comment) {
        return commentRepository.existsById(comment.getId()) && commentRepository.save(comment) != null;
    }

    @Override
    public boolean deleteCommentById(String id) {
        if (commentRepository.existsById(id)) {
            commentRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCommentsByIds(List<String> ids) {
        Iterable<Comment> comments = commentRepository.findAllById(ids);
        if (comments != null) {
            commentRepository.deleteAll(comments);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCommentsByPostId(String postId) {
        Iterable<Comment> comments = commentRepository.findByPostId(postId);
        if (comments != null) {
            commentRepository.deleteAll(comments);
            return true;
        }
        return false;
    }
}
