package com.instagram.postservice.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {
    @GetMapping
    public String hello() {
        return "Hello World";
    }
    @GetMapping("/{id}")
    public String hello(@PathVariable String id) {
        return "Hello " + id;
    }
    @PostMapping
    public String createPost(@RequestBody String postContent) {
        return "Post created with content: " + postContent;
    }

    // UPDATE POST
    @PutMapping("/{id}")
    public String updatePost(@PathVariable Long id,
                             @RequestBody String updatedContent) {
        return "Post " + id + " updated with content: " + updatedContent;
    }

    // DELETE POST
    @DeleteMapping("/{id}")
    public String deletePost(@PathVariable Long id) {
        return "Post " + id + " deleted";
    }

}
