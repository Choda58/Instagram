package com.instagram.followservice.controller;

import com.instagram.followservice.model.Follow;
import com.instagram.followservice.service.FollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}