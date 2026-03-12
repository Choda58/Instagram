package com.instagram.postservice.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/media")
@CrossOrigin("*")
public class FileController {

    private final Path uploadPath = Paths.get("uploads");

    @GetMapping("/{filename}")
    public Resource getFile(@PathVariable String filename) throws Exception {

        Path file = uploadPath.resolve(filename);

        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("File not found");
        }

        return resource;
    }
}