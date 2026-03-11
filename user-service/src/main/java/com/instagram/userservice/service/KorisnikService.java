package com.instagram.userservice.service;

import com.instagram.userservice.dto.LoginRequest;
import com.instagram.userservice.model.Korisnik;
import com.instagram.userservice.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;
    private final PasswordEncoder passwordEncoder;

    public Korisnik register(Korisnik user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(korisnikRepository.findByUsername(user.getUsername()).isPresent()){
            throw new RuntimeException("Username already exists");
        }

        if(korisnikRepository.findByEmail(user.getEmail()).isPresent()){
            throw new RuntimeException("Email already exists");
        }
        return korisnikRepository.save(user);

    }

    public List<Korisnik> all(){
        return korisnikRepository.findAll();
    }

    public Korisnik login(LoginRequest request){

        Korisnik user = korisnikRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Wrong password");
        }

        return user;
    }
    public Korisnik getUser(Long id){
        return korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    public List<Korisnik> search(String username){
        return korisnikRepository.findByUsernameContaining(username);
    }

    public Korisnik updateUser(Long id, Korisnik updated){

        Korisnik user = korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setName(updated.getName());
        user.setBio(updated.getBio());
        user.setProfilePicture(updated.getProfilePicture());
        user.setPrivateProfile(updated.isPrivateProfile());

        return korisnikRepository.save(user);
    }
    public Korisnik getByUsername(String username){
        return korisnikRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }


}
