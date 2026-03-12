package com.instagram.postservice.repository;

import com.instagram.postservice.entity.LikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<LikeEntity, Long> {

    boolean existsByUserIdAndPostId(Long userId, Long postId);
    LikeEntity getByUserIdAndPostId(Long userId, Long postId);
    void deleteByUserIdAndPostId(Long userId, Long postId);

    int countByPostId(Long postId);
}

