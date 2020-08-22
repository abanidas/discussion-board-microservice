package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Post;
import com.abani.microservice.discussionboard.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> getPostById(String id) {
        return postRepository.findById(id);
    }

    @Override
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    @Override
    public List<Post> getPostsByIds(List<String> ids) {
        return (List<Post>) postRepository.findAllById(ids);
    }

    @Override
    public List<Post> getPostsByUsername(String username) {
        return postRepository.findByUsername(username);
    }

    @Override
    public boolean update(Post post) {
        return postRepository.existsById(post.getId()) && postRepository.save(post) != null;
    }

    @Override
    public boolean deletePostById(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
