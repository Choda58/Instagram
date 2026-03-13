package com.instagram.followservice.service;

import com.instagram.followservice.model.Follow;
import com.instagram.followservice.repository.FollowRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FollowServiceTest {

    @Mock
    private FollowRepository followRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private FollowService followService;

    @Test
    void shouldFollowUser() {

        Long followerId = 1L;
        Long followingId = 2L;

        when(restTemplate.getForObject(anyString(), eq(Object.class)))
                .thenReturn(new Object());

        when(followRepository.findByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.empty());

        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .accepted(false)
                .build();

        when(followRepository.save(any(Follow.class))).thenReturn(follow);

        Follow result = followService.follow(followerId, followingId);

        assertNotNull(result);
        assertEquals(followerId, result.getFollowerId());
        assertEquals(followingId, result.getFollowingId());
    }

    @Test
    void shouldThrowExceptionWhenFollowingSelf() {

        Long userId = 1L;

        assertThrows(RuntimeException.class, () ->
                followService.follow(userId, userId));
    }

    @Test
    void shouldUnfollowUser() {

        Long followerId = 1L;
        Long followingId = 2L;

        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .accepted(true)
                .build();

        when(followRepository.findByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.of(follow));

        followService.unfollow(followerId, followingId);

        verify(followRepository).delete(follow);
    }

    @Test
    void shouldAcceptFollowRequest() {

        Long followerId = 1L;
        Long followingId = 2L;

        Follow follow = Follow.builder()
                .followerId(followerId)
                .followingId(followingId)
                .accepted(false)
                .build();

        when(followRepository.findByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.of(follow));

        when(followRepository.save(any())).thenReturn(follow);

        Follow result = followService.accept(followerId, followingId);

        assertTrue(result.isAccepted());
    }

    @Test
    void shouldReturnFollowers() {

        Long userId = 2L;

        Follow follow = Follow.builder()
                .followerId(1L)
                .followingId(userId)
                .accepted(true)
                .build();

        when(followRepository.findByFollowingIdAndAcceptedTrue(userId))
                .thenReturn(List.of(follow));

        var result = followService.getFollowers(userId);

        assertEquals(1, result.size());
    }

    @Test
    void shouldCheckIfFollowing() {

        Long followerId = 1L;
        Long followingId = 2L;

        when(followRepository.findByFollowerIdAndFollowingId(followerId, followingId))
                .thenReturn(Optional.of(new Follow()));

        boolean result = followService.isFollowing(followerId, followingId);

        assertTrue(result);
    }

    @Test
    void shouldCountFollowers() {

        Long userId = 2L;

        when(followRepository.countByFollowingIdAndAcceptedTrue(userId))
                .thenReturn(5L);

        long result = followService.countFollowers(userId);

        assertEquals(5, result);
    }

}