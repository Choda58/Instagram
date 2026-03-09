package com.instagram.postservice.entity;

import com.instagram.postservice.enums.MediaType;
import jakarta.persistence.*;

@Entity
public class Media {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String url;

    private Long size;

    @Enumerated(EnumType.STRING)
    private MediaType type;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private PostEntity post;
}