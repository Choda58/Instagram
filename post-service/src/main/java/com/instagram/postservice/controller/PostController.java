package com.instagram.postservice.controller;

import com.instagram.postservice.entity.PostEntity;
import com.instagram.postservice.service.FileStorageService;
import com.instagram.postservice.service.PostService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/posts")
@CrossOrigin(origins = "*") // allows Angular frontend
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

    // GET all posts of a specific user
    @GetMapping("/user/{userId}")
    public List<PostEntity> getPostsByUser(@PathVariable Long userId) {
        return postService.getPostsByUser(userId);
    }

    // CREATE post with media
    @PostMapping
    public PostEntity createPost(
            @RequestParam String userId,
            @RequestParam String description,
            @RequestParam(required = false) List<MultipartFile> files) {

        if (files != null && files.size() > 20) {
            throw new RuntimeException("Maximum 20 media items allowed");
        }
        Long L = Long.parseLong(userId);
        PostEntity post = new PostEntity();
        post.setUserId(L);
        post.setDescription(description);

        List<String> mediaPaths = new ArrayList<>();

        if (files != null) {
            for (MultipartFile file : files) {

                // max 50MB
                if (file.getSize() > 50_000_000) {
                    throw new RuntimeException("File exceeds 50MB");
                }

                String fileName = file.getOriginalFilename().toLowerCase();

                // only image/video
                if (!fileName.endsWith(".jpg") &&
                        !fileName.endsWith(".jpeg") &&
                        !fileName.endsWith(".png") &&
                        !fileName.endsWith(".mp4") &&
                        !fileName.endsWith(".mov")) {

                    throw new RuntimeException("Only image or video allowed");
                }

                String path = fileStorageService.saveFile(file);
                mediaPaths.add(path);
            }
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

        return "Post deleted successfully";
    }

    //vreme objave hronoloski
    @GetMapping("/ordered")
    public List<PostEntity> Orderedlist() {
        return postService.getOrederedlist();
    }


    @GetMapping("/media/{filename}")
    public ResponseEntity<Resource> getMedia(@PathVariable String filename) {

        try {

            Path filePath = Paths.get("uploads").resolve(filename);

            Resource resource = new UrlResource(filePath.toUri());

            if(resource.exists() && resource.isReadable()) {

                return ResponseEntity
                        .ok()
                        .header("Content-Type", "image/jpeg")
                        .body(resource);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.notFound().build();
    }
    }