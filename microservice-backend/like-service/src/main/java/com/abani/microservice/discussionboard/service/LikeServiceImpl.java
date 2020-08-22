package com.abani.microservice.discussionboard.service;

import com.abani.microservice.discussionboard.model.Like;
import com.abani.microservice.discussionboard.repository.LikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LikeServiceImpl implements LikeService{

    @Autowired
    private LikeRepository likeRepository;

    @Override
    public Like save(Like like) {
        return likeRepository.save(like);
    }

    @Override
    public Optional<Like> getLikeById(String id) {
        return likeRepository.findById(id);
    }

    @Override
    public List<Like> getAllLikes() {
        return likeRepository.findAll();
    }

    @Override
    public List<Like> getLikesByIds(List<String> ids) {
        return (List<Like>) likeRepository.findAllById(ids);
    }

    @Override
    public List<Like> getLikesByPostTypeAndId(String postType, String postId) {
        return likeRepository.findByPostTypeAndId(postType, postId);
    }

    @Override
    public boolean update(Like like) {
        return likeRepository.existsById(like.getId()) && likeRepository.save(like) != null;
    }

    @Override
    public boolean deleteLikeById(String id) {
        if (likeRepository.existsById(id)) {
            likeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLikesByIds(List<String> ids) {
        Iterable<Like> likes = likeRepository.findAllById(ids);
        if (likes != null) {
            likeRepository.deleteAll(likes);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteLikesByPostTypeAndId(String postType, String postId) {
        Iterable<Like> likes = likeRepository.findByPostTypeAndId(postType, postId);
        if (likes != null) {
            likeRepository.deleteAll(likes);
            return true;
        }
        return false;
    }
}
