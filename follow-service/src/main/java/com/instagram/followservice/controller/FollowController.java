package com.instagram.followservice.controller;

import com.instagram.followservice.model.Follow;
import com.instagram.followservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
@RequiredArgsConstructor
public class FollowController {

    private final FollowService followService;

    @PostMapping("/follow")
    public Follow follow(@RequestParam Long followerId,
                         @RequestParam Long followingId){

        return followService.follow(followerId, followingId);
    }

    @DeleteMapping("/unfollow")
    public void unfollow(@RequestParam Long followerId,
                         @RequestParam Long followingId){

        followService.unfollow(followerId, followingId);
    }

    @PostMapping("/accept")
    public Follow accept(@RequestParam Long followerId,
                         @RequestParam Long followingId){

        return followService.accept(followerId, followingId);
    }
    @GetMapping("/followers/{userId}")
    public List<Follow> followers(@PathVariable Long userId){
        return followService.getFollowers(userId);
    }
    @GetMapping("/following/{userId}")
    public List<Follow> following(@PathVariable Long userId){
        return followService.getFollowing(userId);
    }
    @PostMapping("/reject")
    public void reject(@RequestParam Long followerId,
                       @RequestParam Long followingId){

        followService.reject(followerId, followingId);
    }
    @GetMapping("/status")
    public boolean isFollowing(@RequestParam Long followerId,
                               @RequestParam Long followingId){

        return followService.isFollowing(followerId, followingId);
    }
    @GetMapping("/requests/{userId}")
    public List<Follow> requests(@PathVariable Long userId){
        return followService.getPendingRequests(userId);
    }
}