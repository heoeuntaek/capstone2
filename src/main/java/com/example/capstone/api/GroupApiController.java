package com.example.capstone.api;

import com.example.capstone.dto.GroupDto;
import com.example.capstone.entity.Group_tbl;
import com.example.capstone.repository.GroupRepository;
import com.example.capstone.repository.UserRepository;
import com.example.capstone.service.GroupService;
import com.example.capstone.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class GroupApiController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    @PatchMapping("/api/group/{user_login_id}")
    public ResponseEntity<Group_tbl> create_group(@RequestBody GroupDto dto, @PathVariable String user_login_id) {
        Group_tbl created = groupService.create_group(dto, user_login_id);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }

    @GetMapping("/api/group/{group_id}")
    public ResponseEntity<Group_tbl>find_group_by_id(@PathVariable Long group_id) {
        Group_tbl found = groupService.find_group_by_id(group_id);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }



    @DeleteMapping("/api/group/{group_id}/{user_login_id}")
    public ResponseEntity<Group_tbl> delete_group(@PathVariable Long group_id, @PathVariable String user_login_id) {
        Group_tbl deleted = groupService.delete_group(group_id, user_login_id);
        return ResponseEntity.status(HttpStatus.OK).body(deleted);
    }

    @GetMapping("/api/groupcode/{group_code}")
    public ResponseEntity<Group_tbl>findby_group_code(@PathVariable String group_code) {
        Group_tbl found = groupService.find_group_by_code(group_code);
        return ResponseEntity.status(HttpStatus.OK).body(found);
    }



}