package com.instagram.userservice.service;

import com.instagram.userservice.dto.LoginRequest;
import com.instagram.userservice.model.Korisnik;
import com.instagram.userservice.repository.KorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KorisnikService {

    private final KorisnikRepository korisnikRepository;

    public Korisnik register(Korisnik user) {
        return korisnikRepository.save(user);
    }

    public List<Korisnik> all(){
        return korisnikRepository.findAll();
    }

    public Korisnik login(LoginRequest request){

        Korisnik user = korisnikRepository
                .findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if(!user.getPassword().equals(request.getPassword())){
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
}
