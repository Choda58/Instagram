package com.instagram.postservice.controller;

import com.instagram.postservice.service.LikeService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@CrossOrigin(origins = "*")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public String likePost(@RequestParam Long userId,
                           @RequestParam Long postId) {

        likeService.likePost(userId, postId);
        return "Post liked";
    }

    @DeleteMapping
    public String unlikePost(@RequestParam Long userId,
                             @RequestParam Long postId) {

        likeService.unlikePost(userId, postId);
        return "Post unliked";
    }

    @GetMapping("/count/{postId}")
    public int getLikes(@PathVariable Long postId) {

        return likeService.countLikes(postId);
    }
}
