package com.instagram.userservice.service;

import com.instagram.userservice.model.User;
import com.instagram.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User register(User user) {
        return userRepository.save(user);
    }

    public List<User> search(String query){
        return userRepository.findAll();
    }

}
