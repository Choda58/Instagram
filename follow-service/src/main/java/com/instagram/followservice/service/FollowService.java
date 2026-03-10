package com.instagram.followservice.service;

import com.instagram.followservice.model.Follow;
import com.instagram.followservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    public Follow follow(Long followerId, Long followingId){

        if (followerId.equals(followingId)) {
            throw new RuntimeException("User cannot follow themselves");
        }

        if (followRepository.findByFollowerIdAndFollowingId(followerId, followingId).isPresent()) {
            throw new RuntimeException("Already following this user");
        }

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
    public List<Follow> getFollowers(Long userId){
        return followRepository.findByFollowingIdAndAcceptedTrue(userId);
    }
    public List<Follow> getFollowing(Long userId){
        return followRepository.findByFollowerIdAndAcceptedTrue(userId);
    }
    public void reject(Long followerId, Long followingId){

        Follow follow = followRepository
                .findByFollowerIdAndFollowingId(followerId, followingId)
                .orElseThrow();

        followRepository.delete(follow);
    }
    public boolean isFollowing(Long followerId, Long followingId){
        return followRepository
                .findByFollowerIdAndFollowingId(followerId, followingId)
                .isPresent();
    }
    public List<Follow> getPendingRequests(Long userId){
        return followRepository.findByFollowingIdAndAcceptedFalse(userId);
    }
}