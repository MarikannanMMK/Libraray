package com.mmk.library.controller;


import com.mmk.library.entity.Book;
import com.mmk.library.entity.User;
import com.mmk.library.model.EmailDetails;
import com.mmk.library.repository.UserRepository;
import com.mmk.library.service.UserService;
import com.mmk.library.utility.Utill;
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

    @Autowired
    private Utill utill;


    @PostMapping(value = "/newUser")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for creating the new user by providing User Object")
    public ResponseEntity<User> createUser(@RequestBody User user){
            User newUser = userService.createUser(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping(value = "/allUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for fetching all the Users")
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> userList = userService.getAllUsers();
        return new ResponseEntity<List<User>>(userList,HttpStatus.OK);
    }

    @DeleteMapping(value = "unAssign/{userId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for Delete User object By Providing User ID as input")
    public ResponseEntity<String> disableUserById(@PathVariable Integer userId) {
        userService.disableUserById(userId);
        String msg = "User With ID :" + userId + " disable Successfully";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }
    @PutMapping(value = "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for update the User Details and this method returns Updated User Object")
    public ResponseEntity<User> updateBook(@Valid @RequestBody User user) {
        User updatedUser = userService.updateUser(user);
        return new ResponseEntity<User>(updatedUser, HttpStatus.ACCEPTED);
    }

    @PostMapping("/searchByKeyword")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "This Method is for fetching User object By Providing a keyword")
    public ResponseEntity<List<User>> searchByKeyword(@RequestParam String keyWord){
        List<User> searchUserList = userService.searchByKeyword(keyWord);
        return  new ResponseEntity<List<User>>(searchUserList , HttpStatus.OK);

    }


}
