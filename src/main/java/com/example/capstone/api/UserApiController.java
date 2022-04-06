package com.example.capstone.api;

import com.example.capstone.dto.UserDto;
import com.example.capstone.entity.User;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserApiController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/api/users")
    public List<User> findAll() {

        return userService.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }


}