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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


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


    @PostMapping("/api/group/{user_id}")
    public ResponseEntity<Group_tbl> create_group(@RequestBody GroupDto dto, @PathVariable Long user_id) {
        Group_tbl created = groupService.create_group(dto, user_id);
        return ResponseEntity.status(HttpStatus.OK).body(created);
    }


}