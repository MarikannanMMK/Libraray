package com.mmk.library.service;

import com.mmk.library.entity.User;
import com.mmk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User createUser(User user) {

        User newUser = new User();
        newUser.setEmail(user.getEmail());
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setMember(user.getMember());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setMemberId(user.getMemberId());
        newUser.setRole(user.getRole());
        return userRepository.save(newUser);

    }
}
