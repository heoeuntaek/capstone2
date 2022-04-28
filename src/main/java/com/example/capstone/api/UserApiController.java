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
    public ResponseEntity<User> create(@RequestBody UserDto dto) {
        User created = userService.create(dto);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @PostMapping("/api/login")
    public ResponseEntity<User> login(@RequestBody UserDto dto) {    //HTTP요청의 내용을 객체에 매핑하기 위해 @RequestBody 를 설정.
        User user = userService.login(dto);
        log.info("로그인 성공");
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("test")
    public Schedule test(){
        return schedultRepository.findById(2L).get();
    }

    @PatchMapping("/api/groupcode/{group_code}/{user_login_id}")
    public ResponseEntity<User> participe_group(@PathVariable String group_code, @PathVariable String user_login_id) {
        //group_code로 그룹 객체 조회
        Group_tbl group_found = groupService.find_group_by_code(group_code);

        //user_login_id로 유저 객체 조회
        User user_target = userService.findByUser_login_id(user_login_id);

        //유저객체에 그룹 객체 추가
        user_target.setGroup_tbl(group_found);

        //유저 객체 저장
        userRepository.save(user_target);
        log.info("user_target 저장성공{}",user_target);

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