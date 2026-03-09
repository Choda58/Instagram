package com.instagram.postservice.controller;

import com.instagram.postservice.model.Post;
import com.instagram.postservice.service.FileStorageService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    private List<Post> posts = new ArrayList<>();
    private Long idCounter = 1L;

    private final FileStorageService fileStorageService;

    public PostController(FileStorageService fileStorageService) {
        this.fileStorageService = fileStorageService;
    }

    // GET all posts
    @GetMapping
    public List<Post> getPosts() {
        return posts;
    }

    // GET post by id
    @GetMapping("/{id}")
    public Post getPost(@PathVariable Long id) {

        return posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }

    // CREATE post with media
    @PostMapping
    public Post createPost(
            @RequestParam String description,
            @RequestParam List<MultipartFile> files) {

       /* if (files.size() > 20) {
            throw new RuntimeException("Maximum 20 media items allowed");
        }*/

        Post post = new Post();
        post.setId(idCounter++);
        post.setDescription(description);

        List<String> mediaPaths = new ArrayList<>();

        for (MultipartFile file : files) {

            // rule: max 50MB
            if (file.getSize() > 50_000_000) {
                throw new RuntimeException("File exceeds 50MB");
            }

            String fileName = file.getOriginalFilename();

            // rule: only photo/video
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

        post.setMedia(mediaPaths);
        posts.add(post);

        return post;
    }

    // UPDATE description
    @PutMapping("/{id}")
    public Post updateDescription(
            @PathVariable Long id,
            @RequestBody String description) {

        Post post = posts.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Post not found"));

        post.setDescription(description);

        return post;
    }

    // DELETE post
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {

        posts.removeIf(post -> post.getId().equals(id));

        return "Post deleted";
    }
}