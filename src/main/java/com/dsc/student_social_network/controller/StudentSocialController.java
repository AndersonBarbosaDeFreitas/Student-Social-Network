package com.dsc.student_social_network.controller;

import com.dsc.student_social_network.dto.UserDto;
import com.dsc.student_social_network.entity.User;
import com.dsc.student_social_network.exception.UserExistsException;
import com.dsc.student_social_network.exception.UserInvalidException;
import com.dsc.student_social_network.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class StudentSocialController {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new UserDto(new User()), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> addUser(@RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (UserExistsException e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody User user) {
        try {
            return new ResponseEntity<>(userService.updateUser(id, user), HttpStatus.OK);
        } catch (UserInvalidException e) {
            return new ResponseEntity<>(new UserDto(new User()), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserDto> removeUser(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userService.removeUser(id), HttpStatus.OK);
        } catch (UserInvalidException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

}
