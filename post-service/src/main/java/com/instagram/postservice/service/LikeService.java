package com.instagram.postservice.service;

import com.instagram.postservice.entity.LikeEntity;
import com.instagram.postservice.repository.LikeRepository;
import com.instagram.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final PostRepository postRepository;

    public LikeService(LikeRepository likeRepository,
                       PostRepository postRepository) {
        this.likeRepository = likeRepository;
        this.postRepository = postRepository;
    }

    public void likePost(Long userId, Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }

        if (likeRepository.existsByUserIdAndPostId(userId, postId)) {
            throw new RuntimeException("You already liked this post");
        }

        LikeEntity like = new LikeEntity();
        like.setUserId(userId);
        like.setPostId(postId);

        likeRepository.save(like);
    }

    public void unlikePost(Long userId, Long postId) {
        LikeEntity le = likeRepository.getByUserIdAndPostId(userId, postId);
        if (le == null) {
            throw new RuntimeException("You haven't liked this post");
        }

        likeRepository.delete(le);
    }

    public int getLikesCount(Long postId) {
        return likeRepository.countByPostId(postId);
    }

    public int countLikes(Long postId) {
        return likeRepository.countByPostId(postId);
    }
}