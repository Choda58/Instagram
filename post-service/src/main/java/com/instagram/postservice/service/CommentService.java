package com.instagram.postservice.service;

import com.instagram.postservice.entity.CommentEntity;
import com.instagram.postservice.repository.CommentRepository;
import com.instagram.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public CommentService(CommentRepository commentRepository,
                          PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }

    public CommentEntity addComment(CommentEntity comment) {

        if (!postRepository.existsById(comment.getPostId())) {
            throw new RuntimeException("Post not found");
        }

        if (comment.getText() == null || comment.getText().trim().isEmpty()) {
            throw new RuntimeException("Comment cannot be empty");
        }

        return commentRepository.save(comment);
    }

    public List<CommentEntity> getComments(Long postId) {

        if (!postRepository.existsById(postId)) {
            throw new RuntimeException("Post not found");
        }

        return commentRepository.findByPostId(postId);
    }

    public CommentEntity updateComment(Long id, String text) {

        CommentEntity comment = commentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (text == null || text.trim().isEmpty()) {
            throw new RuntimeException("Comment cannot be empty");
        }

        comment.setText(text);

        return commentRepository.save(comment);
    }

    public void deleteComment(Long id) {

        if (!commentRepository.existsById(id)) {
            throw new RuntimeException("Comment not found");
        }

        commentRepository.deleteById(id);
    }

    public int countComments(Long postId) {
        return commentRepository.countByPostId(postId);
    }
}