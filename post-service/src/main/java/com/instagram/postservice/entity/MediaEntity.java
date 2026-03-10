package com.instagram.postservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.instagram.postservice.enums.MediaType;
import jakarta.persistence.*;

@Entity
public class MediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private Long size;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonBackReference
    private PostEntity post;

    public Long getId() { return id; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public Long getSize() { return size; }
    public void setSize(Long size) { this.size = size; }

    public MediaType getType() { return type; }
    public void setType(MediaType type) { this.type = type; }

    public PostEntity getPost() { return post; }
    public void setPost(PostEntity post) { this.post = post; }
}