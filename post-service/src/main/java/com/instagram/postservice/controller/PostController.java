package com.instagram.postservice.controller;

import com.instagram.postservice.entity.PostEntity;
import com.instagram.postservice.service.FileStorageService;
import com.instagram.postservice.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final FileStorageService fileStorageService;
    private final PostService postService;

    public PostController(FileStorageService fileStorageService,
                          PostService postService) {
        this.fileStorageService = fileStorageService;
        this.postService = postService;
    }

    // GET all posts
    @GetMapping
    public List<PostEntity> getPosts() {
        return postService.getPosts();
    }

    // GET post by id
    @GetMapping("/{id}")
    public PostEntity getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // CREATE post with media
    @PostMapping
    public PostEntity createPost(
            @RequestParam String description,
            @RequestParam List<MultipartFile> files) {

        if (files.size() > 20) {
            throw new RuntimeException("Maximum 20 media items allowed");
        }

        PostEntity post = new PostEntity();
        post.setDescription(description);

        List<String> mediaPaths = new ArrayList<>();

        for (MultipartFile file : files) {

            // max 50MB
            if (file.getSize() > 50_000_000) {
                throw new RuntimeException("File exceeds 50MB");
            }

            String fileName = file.getOriginalFilename();

            // only photo/video
            if (!fileName.endsWith(".jpg") &&
                    !fileName.endsWith(".jpeg") &&
                    !fileName.endsWith(".png") &&
                    !fileName.endsWith(".mp4") &&
                    !fileName.endsWith(".mov")) {

                throw new RuntimeException("Only photo or video allowed");
            }

            String path = fileStorageService.saveFile(file);
            mediaPaths.add(path);
        }

        return postService.savePost(post, mediaPaths);
    }

    // UPDATE description
    @PutMapping("/{id}")
    public PostEntity updateDescription(
            @PathVariable Long id,
            @RequestBody String description) {

        return postService.updateDescription(id, description);
    }

    // DELETE post
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {

        postService.deletePost(id);

        return "Post deleted";
    }
}