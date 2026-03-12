package com.instagram.postservice.controller;

import com.instagram.postservice.entity.CommentEntity;
import com.instagram.postservice.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
@CrossOrigin(origins = "*")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public CommentEntity addComment(@RequestBody CommentEntity comment) {

        return commentService.addComment(comment);
    }

    @GetMapping("/{postId}")
    public List<CommentEntity> getComments(@PathVariable Long postId) {

        return commentService.getComments(postId);
    }

    @PutMapping("/{id}")
    public CommentEntity updateComment(@PathVariable Long id,
                                       @RequestBody String text) {

        return commentService.updateComment(id, text);
    }

    @DeleteMapping("/{id}")
    public String deleteComment(@PathVariable Long id) {

        commentService.deleteComment(id);

        return "Comment deleted";
    }
}
