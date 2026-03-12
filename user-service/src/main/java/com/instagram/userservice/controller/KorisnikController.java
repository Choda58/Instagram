package com.instagram.userservice.controller;

import com.instagram.userservice.dto.KorisnikResponse;
import com.instagram.userservice.dto.LoginRequest;
import com.instagram.userservice.model.Korisnik;
import com.instagram.userservice.service.BlockKorisnikService;
import com.instagram.userservice.service.KorisnikService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class KorisnikController {

    private final KorisnikService korisnikService;
    private final BlockKorisnikService blockService;

    @PostMapping("/register")
    public ResponseEntity<Korisnik> register(@RequestBody @Valid Korisnik korisnik) {
        return ResponseEntity.ok(korisnikService.register(korisnik));
    }
    @GetMapping
    public ResponseEntity<List<Korisnik>> getAllUsers() {
        return ResponseEntity.ok(korisnikService.all());
    }

    @PostMapping("/login")
    public ResponseEntity<KorisnikResponse> login(@RequestBody LoginRequest request){

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
    @PutMapping("/edit/{id}")
    public Korisnik updateUser(@PathVariable Long id, @RequestBody Korisnik updated){
        return korisnikService.updateUser(id, updated);
    }
    @GetMapping("/username/{username}")
    public Korisnik getByUsername(@PathVariable String username){
        return korisnikService.getByUsername(username);
    }

    @PostMapping("/{userId}/block/{blockedId}")
    public ResponseEntity<?> blockUser(@PathVariable Long userId,
                                       @PathVariable Long blockedId) {

        blockService.blockUser(userId, blockedId);
        return ResponseEntity.ok("User blocked");
    }
    @DeleteMapping("/{userId}/block/{blockedId}")
    public ResponseEntity<?> unblockUser(@PathVariable Long userId,
                                         @PathVariable Long blockedId) {

        blockService.unblockUser(userId, blockedId);
        return ResponseEntity.ok("User unblocked");
    }


}
