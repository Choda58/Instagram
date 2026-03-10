package com.instagram.postservice.repository;

import com.instagram.postservice.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity, Long> {
    
}