package com.instagram.userservice.repository;

import com.instagram.userservice.model.BlockKorisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BlockKorisnikRepository extends JpaRepository<BlockKorisnik, Long> {

    Optional<BlockKorisnik> findByBlockerIdAndBlockedId(Long blockerId, Long blockedId);

    void deleteByBlockerIdAndBlockedId(Long blockerId, Long blockedId);
}
