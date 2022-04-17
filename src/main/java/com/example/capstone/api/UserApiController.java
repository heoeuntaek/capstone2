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
import java.util.Map;

@RestController
public class UserApiController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;
//
    @GetMapping("/api/users")
    public List<User> findAll() {

        return userService.findAll();
    }

    @GetMapping("/api/users/{id}")
    public User findOne(@PathVariable long id) {
        return userService.findOne(id);
    }



//    @PostMapping("/api/users")
//    public ResponseEntity<User> create(@RequestBody UserDto dto) {
//        User created = userService.create(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(created);
//    }

//    @GetMapping("/api/users") // 파라미터로 받음
//    public User findOne(@RequestParam(value = "id", required = false) Long id) {
//        return userService.findOne(id);
//    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(UserDto dto) {
//    public ResponseEntity<User> create(@Body UserDto dto) {
        User created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

//    @PostMapping("/api/users{id}")
//    public User create(@PathVariable Long id){
//        return userService.create(id);
//    }
//        User created = userService.create(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(created);
//@PatchMapping("/api/articles/{id}")
//public ResponseEntity<User> update(@PathVariable Long id,  // responseEntity - 상태코드 실을수있음
//                                      //response Entity 에 Article이 담겨서 들어감
//                                      @RequestBody UserDto dto) {  //json 형태의 데이터가 받아짐
//
//    User updated = userService.update(id, dto);
//    return (updated != null) ?  // 비어있지 않다면
//            ResponseEntity.status(HttpStatus.OK).body(updated) :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//}












}