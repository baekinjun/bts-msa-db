package com.bts.db.repository;

import com.bts.db.domain.Like;
import com.bts.db.domain.NFT;
import com.bts.db.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like,Long> {
    Optional<List<Like>> findByuserId(User id);
    Optional<List<Like>> findByno(NFT no);
}
