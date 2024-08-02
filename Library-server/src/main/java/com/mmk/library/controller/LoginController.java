package com.mmk.library.controller;

import com.mmk.library.config.JWTUtil;
import com.mmk.library.entity.UserRefreshToken;
import com.mmk.library.exception.RefreshTokenNotFoundException;
import com.mmk.library.model.RefreshToken;
import com.mmk.library.model.UserRequest;
import com.mmk.library.model.UserToken;
import com.mmk.library.service.RefreshTokenService;
import com.mmk.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RefreshTokenService refreshTokenService;


    @PostMapping(value = "/login")
    public ResponseEntity<UserToken> login(@RequestBody UserRequest userRequest) {
        //System.out.println("User name : "+userRequest.getUserName()+" password : "+userRequest.getPassword());
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userRequest.getUserName(), userRequest.getPassword()));
        refreshTokenService.deleteRefreshTokenIsAvailable(userRequest.getUserName());
        UserRefreshToken userRefreshToken = refreshTokenService.createRefreshToken(userRequest.getUserName());
        UserToken accessToken = UserToken.builder().AccessToken(jwtUtil.generateToken(userRequest.getUserName()))
                .RefreshToken(userRefreshToken.getToken())
                .build();
        return new ResponseEntity<UserToken>(accessToken, HttpStatus.OK);
    }

    @PostMapping(value = "/refreshToken")
    public ResponseEntity<UserToken> createRefreshToken(@RequestBody RefreshToken refreshToken) {
        return refreshTokenService.findByToken(refreshToken.getRefreshToken())
                .map(refreshTokenService::VerifyTokenExpiration)
                .map(UserRefreshToken::getUser)
                .map(userInfo -> {
                    String accessToken = jwtUtil.generateToken(userInfo.getEmail());
                    System.out.println("accessToken in refresh api " + accessToken);
                    return new ResponseEntity<UserToken>(UserToken.builder()
                            .AccessToken(accessToken)
                            .RefreshToken(refreshToken.getRefreshToken()).build(), HttpStatus.CREATED);
                }).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh Token is not in DB..!!"));
    }

}