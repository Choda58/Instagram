package com.instagram.postservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.instagram.postservice.entity.PostEntity;
import com.instagram.postservice.repository.PostRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PostControllerApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PostRepository postRepository;


    @Test
    void shouldReturnAllPosts() throws Exception {

        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCreatePost() throws Exception {

        mockMvc.perform(post("/posts")
                        .param("userId", "1")
                        .param("description", "Test post"))
                .andExpect(status().isOk());
    }

    @Test
    void shouldDeletePost() throws Exception {

        PostEntity post = new PostEntity();
        post.setDescription("Delete test");

        PostEntity saved = postRepository.save(post);

        mockMvc.perform(delete("/posts/" + saved.getId()))
                .andExpect(status().isOk());
    }
    @Test
    void shouldReturnPostById() throws Exception {

        PostEntity post = new PostEntity();
        post.setDescription("Find test");

        PostEntity saved = postRepository.save(post);

        mockMvc.perform(get("/posts/" + saved.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.description").value("Find test"));
    }
}