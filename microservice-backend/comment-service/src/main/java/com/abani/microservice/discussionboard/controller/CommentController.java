package com.abani.microservice.discussionboard.controller;

import com.abani.microservice.discussionboard.model.Comment;
import com.abani.microservice.discussionboard.model.IdList;
import com.abani.microservice.discussionboard.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/comments")
    public ResponseEntity<Comment> addComment(@RequestBody Comment comment){

        comment.setCreateTime(LocalDateTime.now());
        Comment saved = commentService.save(comment);
        String updateUrl = "http://post-service/posts/"+comment.getPostId()+"/comments/"+saved.getId();

        try {
            restTemplate.put(updateUrl, null);
        }
        catch (IllegalStateException e) {
            commentService.deleteCommentById(saved.getId());
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        //WebClient.create("http://post-service").put().uri(updateUrl).retrieve().bodyToMono(ResponseEntity.class).toFuture();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getAllComments(){
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping("/comments/posts/{post_id}")
    public ResponseEntity<List<Comment>> getAllCommentsOfPost(@PathVariable("post_id") String postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getSingleComment(@PathVariable("id") String id){
        return ResponseEntity.ok(commentService.getCommentById(id).orElse(null));
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<String> deleteComment(@PathVariable("id") String id){
        return commentService.deleteCommentById(id) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/comments/posts/{post_id}")
    public ResponseEntity<String> deleteComments(@PathVariable("post_id") String postId){
        return commentService.deleteCommentsByPostId(postId) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }

    @PutMapping("/comments/{id}/likes/{like_id}")
    public ResponseEntity<String> addLike(@PathVariable("id") String id, @PathVariable("like_id") String likeId){
        Comment comment = commentService.getCommentById(id).orElse(null);
        if(comment == null) {
            return ResponseEntity.notFound().build();
        }
        if(comment.getLikeIds() == null) {
            comment.setLikeIds(new ArrayList<>());
        }
        comment.getLikeIds().add(likeId);
        return commentService.update(comment) ? ResponseEntity.ok("Updated Successfully") : ResponseEntity.notFound().build();
    }
}
