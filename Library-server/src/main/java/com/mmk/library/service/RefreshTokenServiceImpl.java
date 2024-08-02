package com.mmk.library.service;

import com.mmk.library.config.JWTUtil;
import com.mmk.library.entity.User;
import com.mmk.library.entity.UserRefreshToken;
import com.mmk.library.repository.UserRefreshTokenRepository;
import com.mmk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class RefreshTokenServiceImpl implements RefreshTokenService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRefreshTokenRepository userRefreshTokenRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Override
    public UserRefreshToken createRefreshToken(String userName) {
        UserRefreshToken userRefreshToken = UserRefreshToken.builder()
                .user(userRepository.findByEmail(userName))
                .token(jwtUtil.generateRefreshToken(userName))
                .build();
        return userRefreshTokenRepository.save(userRefreshToken);
    }

    @Override
    public Optional<UserRefreshToken> findByToken(String token) {
        return userRefreshTokenRepository.findByToken(token);
    }

    @Override
    public UserRefreshToken VerifyTokenExpiration(UserRefreshToken refreshToken) {

        if (jwtUtil.isTokenExpired(refreshToken.getToken())){
            userRefreshTokenRepository.delete(refreshToken);
            throw new RuntimeException(refreshToken.getToken() +" Refresh token is expired. Please make a new login..! ");
        }

        return refreshToken;
    }

    @Override
    public void deleteRefreshTokenIsAvailable(String userName) {
        User user = userRepository.findByEmail(userName);
        int userId = user.getId();
        UserRefreshToken userRefreshToken =  userRefreshTokenRepository.findByUserId(userId);
        System.out.println(userRefreshToken.getUser().getId());
        if(userRefreshToken != null){
            userRefreshTokenRepository.delete(userRefreshToken);
        }

    }
}
