package com.instagram.followservice.repository;

import com.instagram.followservice.model.Follow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowRepository extends JpaRepository<Follow, Long> {

    Optional<Follow> findByFollowerIdAndFollowingId(Long followerId, Long followingId);
    List<Follow> findByFollowingIdAndAcceptedTrue(Long followingId);
    List<Follow> findByFollowerIdAndAcceptedTrue(Long followerId);
    List<Follow> findByFollowingIdAndAcceptedFalse(Long followingId);
}

