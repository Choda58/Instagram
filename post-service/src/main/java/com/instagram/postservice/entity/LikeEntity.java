package com.instagram.postservice.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"userId","postId"})) //nova dodata
public class LikeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    private Long postId;

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }
}