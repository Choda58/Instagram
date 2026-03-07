package com.instagram.userservice.controller;

import com.instagram.userservice.dto.LoginRequest;
import com.instagram.userservice.model.Korisnik;
import com.instagram.userservice.service.KorisnikService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;

    @PostMapping("/register")
    public ResponseEntity<Korisnik> register(@RequestBody Korisnik korisnik){
        return ResponseEntity.ok(korisnikService.register(korisnik));
    }
    @GetMapping
    public ResponseEntity<List<Korisnik>> getAllUsers() {
        return ResponseEntity.ok(korisnikService.all());
    }

    @PostMapping("/login")
    public ResponseEntity<Korisnik> login(@RequestBody LoginRequest request){

        return ResponseEntity.ok(korisnikService.login(request));

    }
    @GetMapping("/{id}")
    public Korisnik getUser(@PathVariable Long id){
        return korisnikService.getUser(id);
    }
    @GetMapping("/search")
    public List<Korisnik> search(@RequestParam String username){
        return korisnikService.search(username);
    }

}
