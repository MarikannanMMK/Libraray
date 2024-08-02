package com.mmk.library.repository;

import com.mmk.library.entity.UserRefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRefreshTokenRepository extends JpaRepository<UserRefreshToken,Integer> {
    Optional<UserRefreshToken> findByToken(String token);

    UserRefreshToken findByUserId(int userId);
}
