package com.instagram.followservice.dto;

public class FollowResponseDTO {

    private Long followerId;
    private Long followingId;

    public FollowResponseDTO(Long followerId, Long followingId) {
        this.followerId = followerId;
        this.followingId = followingId;
    }

    public Long getFollowerId() {
        return followerId;
    }

    public Long getFollowingId() {
        return followingId;
    }
}