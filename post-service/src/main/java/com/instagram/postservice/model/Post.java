package com.instagram.postservice.model;

import java.util.ArrayList;
import java.util.List;

public class Post {

    private Long id;
    private String description;
    private List<String> media = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }
}