package com.instagram.userservice.service;

import com.instagram.userservice.model.BlockKorisnik;
import com.instagram.userservice.repository.BlockKorisnikRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockKorisnikService {

    private final BlockKorisnikRepository blockRepository;

    public void blockUser(Long blockerId, Long blockedId) {

        if (blockRepository.findByBlockerIdAndBlockedId(blockerId, blockedId).isPresent()) {
            return;
        }

        BlockKorisnik block = new BlockKorisnik(blockerId, blockedId);
        blockRepository.save(block);
    }

    public void unblockUser(Long blockerId, Long blockedId) {

        blockRepository.deleteByBlockerIdAndBlockedId(blockerId, blockedId);
    }

    public boolean isBlocked(Long blockerId, Long blockedId) {

        return blockRepository
                .findByBlockerIdAndBlockedId(blockerId, blockedId)
                .isPresent();
    }
}