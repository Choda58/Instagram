package com.instagram.followservice.controller;

import com.instagram.followservice.service.FollowService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FollowControllerTest {

    private FollowService followService;
    private FollowController followController;

    @BeforeEach
    void setUp() {
        followService = Mockito.mock(FollowService.class);
        followController = new FollowController(followService);
    }

    @Test
    void shouldFollowUser() {

        Long followerId = 1L;
        Long followingId = 2L;

        followController.follow(followerId, followingId);

        Mockito.verify(followService).follow(followerId, followingId);
    }

    @Test
    void shouldUnfollowUser() {

        Long followerId = 1L;
        Long followingId = 2L;

        followController.unfollow(followerId, followingId);

        Mockito.verify(followService).unfollow(followerId, followingId);
    }
}