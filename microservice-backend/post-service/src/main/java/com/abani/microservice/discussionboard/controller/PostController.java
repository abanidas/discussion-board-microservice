package com.abani.microservice.discussionboard.controller;

import com.abani.microservice.discussionboard.model.Post;
import com.abani.microservice.discussionboard.model.PostStatus;
import com.abani.microservice.discussionboard.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@CrossOrigin
public class PostController {

    @Autowired
    private PostService postService;

    private WebClient webClient;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/posts")
    public ResponseEntity<Post> createPost(@RequestBody Post post){
        PostStatus status = new PostStatus(1, "Active");
        post.setStatus(status);
        post.setCreateTime(LocalDateTime.now());
        Post saved = postService.save(post);
        String updateUrl = "http://user-service/users/"+post.getUsername()+"/posts/"+saved.getId();

        try {
            restTemplate.put(updateUrl, null);
        }
        catch (IllegalStateException e) {
            postService.deletePostById(saved.getId());
            return ResponseEntity.status(HttpStatus.RESET_CONTENT).body(null);
        }
        //webClient.put().uri(updateUrl).retrieve().bodyToMono(ResponseEntity.class).toFuture();
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getAllPosts(){
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/posts/users/{username}")
    public ResponseEntity<List<Post>> getAllPostsOfUser(@PathVariable("username") String username){
        return ResponseEntity.ok(postService.getPostsByUsername(username));
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> getSinglePost(@PathVariable("id") String id){
        return ResponseEntity.ok(postService.getPostById(id).orElse(null));
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") String id){
        return postService.deletePostById(id) ? ResponseEntity.ok("Deleted Successfully") : ResponseEntity.notFound().build();
    }

    @PutMapping("/posts/{id}/comments/{comment_id}")
    public ResponseEntity<String> addComment(@PathVariable("id") String id, @PathVariable("comment_id") String commentId){
        Post post = postService.getPostById(id).orElse(null);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        if(post.getCommentIds() == null) {
            post.setCommentIds(new ArrayList<>());
        }
        post.getCommentIds().add(commentId);
        return postService.update(post) ? ResponseEntity.ok("Updated Successfully") : ResponseEntity.notFound().build();
    }

    @PutMapping("/posts/{id}/likes/{like_id}")
    public ResponseEntity<String> addLike(@PathVariable("id") String id, @PathVariable("like_id") String likeId){
        Post post = postService.getPostById(id).orElse(null);
        if(post == null) {
            return ResponseEntity.notFound().build();
        }
        if(post.getLikeIds() == null) {
            post.setLikeIds(new ArrayList<>());
        }
        post.getLikeIds().add(likeId);
        return postService.update(post) ? ResponseEntity.ok("Updated Successfully") : ResponseEntity.notFound().build();
    }

}
