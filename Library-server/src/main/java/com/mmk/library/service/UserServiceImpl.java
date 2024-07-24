package com.mmk.library.service;


import com.mmk.library.entity.User;
import com.mmk.library.exception.UserNotFoundException;
import com.mmk.library.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

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

    @Override
    public User updateUser(User user) {
        userRepository.findById(user.getId()).orElseThrow(
                () -> new UserNotFoundException("User With Given ID :" + user.getId() + " Not Available"));
        User updatedUser = userRepository.findById(user.getId()).get();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setMemberId(user.getMemberId());
        updatedUser.setMember(user.getMember());
        return userRepository.save(updatedUser);

    }

    @Override
    public void disableUserById(Integer userId) {
        userRepository.findById(userId).orElseThrow(
                () -> new UserNotFoundException("User With Given ID :" + userId + " Not Available"));
        User updatedUser = userRepository.findById(userId).get();
        updatedUser.setIsEnable("No");
        userRepository.save(updatedUser);
    }
}
