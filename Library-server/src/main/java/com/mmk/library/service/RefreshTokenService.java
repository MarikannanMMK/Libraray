package com.mmk.library.service;

import com.mmk.library.entity.UserRefreshToken;
import org.springframework.stereotype.Service;

import java.util.Optional;


public interface RefreshTokenService {

    UserRefreshToken createRefreshToken(String userName);

    Optional<UserRefreshToken> findByToken(String Token);

    UserRefreshToken VerifyTokenExpiration(UserRefreshToken refreshToken);

    void deleteRefreshTokenIsAvailable(String userName);
}
