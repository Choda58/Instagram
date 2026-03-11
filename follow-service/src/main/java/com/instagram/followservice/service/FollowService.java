package com.instagram.followservice.service;

import com.instagram.followservice.dto.FollowResponseDTO;
import com.instagram.followservice.model.Follow;
import com.instagram.followservice.repository.FollowRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FollowService {

    private final FollowRepository followRepository;

    private final RestTemplate restTemplate;
    private void checkUserExists(Long userId){

        String url = "http://localhost:8081/api/users/" + userId;

        restTemplate.getForObject(url, Object.class);

    }

    public Follow follow(Long followerId, Long followingId){

        checkUserExists(followerId);
        checkUserExists(followingId);

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
    public List<FollowResponseDTO> getFollowers(Long userId) {
        return followRepository.findByFollowingIdAndAcceptedTrue(userId)
                .stream()
                .map(f -> new FollowResponseDTO(f.getFollowerId(), f.getFollowingId()))
                .toList();
    }
    public List<FollowResponseDTO> getFollowing(Long userId) {
        return followRepository.findByFollowerIdAndAcceptedTrue(userId)
                .stream()
                .map(f -> new FollowResponseDTO(f.getFollowerId(), f.getFollowingId()))
                .toList();
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

    public long countFollowers(Long userId){
        return followRepository.countByFollowingIdAndAcceptedTrue(userId);
    }

    public long countFollowing(Long userId){
        return followRepository.countByFollowerIdAndAcceptedTrue(userId);
    }
}