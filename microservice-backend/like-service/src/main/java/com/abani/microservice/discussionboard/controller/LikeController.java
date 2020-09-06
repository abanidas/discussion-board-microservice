package com.abani.microservice.discussionboard.controller;

import com.abani.microservice.discussionboard.model.Like;
import com.abani.microservice.discussionboard.model.IdList;
import com.abani.microservice.discussionboard.model.Like;
import com.abani.microservice.discussionboard.service.LikeService;
import com.abani.microservice.discussionboard.service.LikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin
public class LikeController {

    @Autowired
    private LikeService likeService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/likes/posts/{post_id}")
    public ResponseEntity<Like> addPostLike(@PathVariable("post_id") String postId, @RequestBody Like like){

        like.setPostType("POST");
        like.setCreateTime(LocalDateTime.now());
        Like saved = likeService.save(like);
        String updateUrl = "http://post-service/posts/"+postId+"/likes/"+saved.getId();

        try {
            restTemplate.put(updateUrl, null);
        }
        catch (IllegalStateException e) {
            likeService.deleteLikeById(saved.getId());
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        //WebClient.create("http://post-service").put().uri(updateUrl).retrieve().bodyToMono(ResponseEntity.class).toFuture();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PostMapping("/likes/comments/{comment_id}")
    public ResponseEntity<Like> addCommentLike(@PathVariable("comment_id") String commentId, @RequestBody Like like){

        like.setPostType("COMMENT");
        like.setCreateTime(LocalDateTime.now());
        Like saved = likeService.save(like);
        String updateUrl = "http://comment-service/comments/"+commentId+"/likes/"+saved.getId();

        try {
            restTemplate.put(updateUrl, null);
        }
        catch (IllegalStateException e) {
            likeService.deleteLikeById(saved.getId());
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        //WebClient.create("http://comment-service").put().uri(updateUrl).retrieve().bodyToMono(ResponseEntity.class).toFuture();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/likes")
    public ResponseEntity<List<Like>> getLikes(){
        return ResponseEntity.ok(likeService.getAllLikes());
    }

    @GetMapping("/likes/posts/{post_id}")
    public ResponseEntity<List<Like>> getPostLikes(@PathVariable("post_id") String postId){
        return ResponseEntity.ok(likeService.getLikesByPostTypeAndId("POST", postId));
    }

    @GetMapping("/likes/comments/{comment_id}")
    public ResponseEntity<List<Like>> getCommentLikes(@PathVariable("comment_id") String commentId){
        return ResponseEntity.ok(likeService.getLikesByPostTypeAndId("COMMENT", commentId));
    }

    @GetMapping("/likes/{id}")
    public ResponseEntity<Like> getSingleLike(@PathVariable("id") String id){
        return ResponseEntity.ok(likeService.getLikeById(id).orElse(null));
    }

    @DeleteMapping("/likes/{id}")
    public ResponseEntity<String> deleteLike(@PathVariable("id") String id){
        return likeService.deleteLikeById(id) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/likes/posts/{post_id}")
    public ResponseEntity<String> deleteAllLikesOfPost(@PathVariable("post_id") String postId){
        return likeService.deleteLikesByPostTypeAndId("POST", postId) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/likes/comments/{comment_id}")
    public ResponseEntity<String> deleteAllLikesOfComment(@PathVariable("comment_id") String commentId){
        return likeService.deleteLikesByPostTypeAndId("COMMENT", commentId) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }
}
