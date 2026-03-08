package com.instagram.followservice.service;

import com.instagram.followservice.model.Follow;
import com.instagram.followservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public Follow follow(Long followerId, Long followingId){

        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .accepted(false)
                .build();

        return followRepository.save(follow);
    }

    public void unfollow(Long followerId, Long followingId){

        Follow follow = followRepository
                .findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow();

        followRepository.delete(follow);
    }

    public Follow accept(Long followerId, Long followingId){

        Follow follow = followRepository
                .findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow();

        follow.setAccepted(true);

        return followRepository.save(follow);
    }
}