package com.example.capstone.api;

import com.example.capstone.dto.UserDto;
import com.example.capstone.entity.Group_tbl;
import com.example.capstone.entity.Schedule;
import com.example.capstone.entity.User;
import com.example.capstone.repository.ScheduleRepository;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.GroupService;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class UserApiController {


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ScheduleRepository schedultRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    //
    @GetMapping("/api/users")
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/api/list/{user_name}")
    public User findByUsername(@PathVariable String user_name) {
        return userService.findByUser_name(user_name);
    }


    @GetMapping("/api/users/{id}")
    public User findbyId(@PathVariable Long id) {
        return userService.findbyId(id);
    }

    @GetMapping("/api/user_id/{user_login_id}")
    public User findByUser_login_id(@PathVariable String user_login_id) {
        return userService.findByUser_login_id(user_login_id);
    }

    @GetMapping("/api/user_id_dto/{user_login_id}")
    public UserDto findByUser_login_id_dto(@PathVariable String user_login_id) {
        return userService.findByUser_login_id_dto(user_login_id);
    }


//    @PostMapping("/api/users")
//    public ResponseEntity<User> create(@RequestBody UserDto dto) {
//        User created = userService.create(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(created);
//    }

//    @GetMapping("/api/users") // ??????????????? ??????
//    public User findOne(@RequestParam(value = "id", required = false) Long id) {
//        return userService.findOne(id);
//    }

    @PostMapping("/api/users")
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody UserDto dto) {    //HTTP????????? ????????? ????????? ???????????? ?????? @RequestBody ??? ??????.
        User user = userService.login(dto);
        log.info("????????? ??????");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("test")
    public Schedule test(){
        return schedultRepository.findById(2L).get();
    }


    //?????? ??????
    @PatchMapping("/api/groupcode/{group_code}/{user_login_id}")
    public ResponseEntity<User> participe_group(@PathVariable String group_code, @PathVariable String user_login_id) {
        //group_code??? ?????? ?????? ??????
        Group_tbl group_found = groupService.find_group_by_code(group_code);

        //user_login_id??? ?????? ?????? ??????
        User user_target = userService.findByUser_login_id(user_login_id);

        //??????????????? ?????? ?????? ??????
        user_target.setGroup_tbl(group_found);

        //?????? ?????? ??????
        userRepository.save(user_target);
        log.info("user_target ????????????{}",user_target);

        return ResponseEntity.status(HttpStatus.OK).body(user_target);
    }
}


//    @PostMapping("/api/users{id}")
//    public User create(@PathVariable Long id){
//        return userService.create(id);
//    }
//        User created = userService.create(dto);
//        return ResponseEntity.status(HttpStatus.OK).body(created);
//@PatchMapping("/api/articles/{id}")
//public ResponseEntity<User> update(@PathVariable Long id,  // responseEntity - ???????????? ???????????????
//                                      //response Entity ??? Article??? ????????? ?????????
//                                      @RequestBody UserDto dto) {  //json ????????? ???????????? ?????????
//
//    User updated = userService.update(id, dto);
//    return (updated != null) ?  // ???????????? ?????????
//            ResponseEntity.status(HttpStatus.OK).body(updated) :
//            ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//}