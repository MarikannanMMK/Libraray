package com.mmk.library.controller;


import com.mmk.library.entity.Book;
import com.mmk.library.entity.User;
import com.mmk.library.repository.UserRepository;
import com.mmk.library.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepositoy;


    @PostMapping(value = "/newUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<User> createUser(@RequestBody User user){
            User newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/allUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @DeleteMapping(value = "unAssign/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for Delete Book object By Providing Book ID as input")
    public ResponseEntity<String> disableUserById(@PathVariable Integer userId) {
        userService.disableUserById(userId);
        String msg = "User With ID :" + userId + " disable Successfully";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for update the Book Details and this method returns Updated Book Object")
    public ResponseEntity<User> updateBook(@Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
    }

    public ResponseEntity<List<User>> searchByKeyword(@RequestParam String keyWord){

        List<User> searchUserList = userService.searchByKeyword(keyWord);

        return  new ResponseEntity<List<User>>(searchUserList , HttpStatus.OK);

    }
}
