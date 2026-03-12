package com.instagram.userservice.repository;

import com.instagram.userservice.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {

    Optional<Korisnik> findByUsername(String username);

    Optional<Korisnik> findByEmail(String email);

    Optional<Korisnik> findByUsernameOrEmail(String username, String email);

    List<Korisnik> findByUsernameContaining(String username);
}
