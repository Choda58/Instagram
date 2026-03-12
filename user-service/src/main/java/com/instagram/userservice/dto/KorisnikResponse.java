package com.instagram.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KorisnikResponse {

    private Long id;
    private String name;
    private String username;
    private String bio;
    private String profilePicture;
    private boolean privateProfile;
}