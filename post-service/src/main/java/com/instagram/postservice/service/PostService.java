package com.instagram.postservice.service;

import com.instagram.postservice.entity.MediaEntity;
import com.instagram.postservice.entity.PostEntity;
import com.instagram.postservice.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public PostEntity savePost(PostEntity post, List<String> mediaPaths) {
        List<MediaEntity> mediaList = mediaPaths.stream()
                .map(path -> {
                    MediaEntity media = new MediaEntity();
                    media.setUrl(path);
                    media.setPost(post);
                    return media;
                })
                .toList();

        post.setMedia(mediaList);
        // later we will convert mediaPaths → MediaEntity entities
        return postRepository.save(post);
    }

    public List<PostEntity> getPosts() {
        return postRepository.findAll();
    }

    public PostEntity getPost(Long id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post not found"));
    }
    public List<PostEntity> getPostsByUser(Long userId) {
        return postRepository.findByUserId(userId);
    }

    public PostEntity updateDescription(Long id, String description) {

        PostEntity post = getPost(id);
        post.setDescription(description);

        return postRepository.save(post);
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }

    public List<PostEntity> getOrederedlist() {
        List<PostEntity>  p = postRepository.findAll();
        p.sort(
                Comparator.comparing(
                        PostEntity::getVremeobjave,
                        Comparator.nullsLast(Date::compareTo)
                ).reversed()
        );

        return p;
    }
}