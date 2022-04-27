package com.example.capstone.service;


import com.example.capstone.dto.GroupDto;
import com.example.capstone.entity.Group_tbl;
import com.example.capstone.entity.User;
import com.example.capstone.repository.GroupRepository;
import com.example.capstone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Slf4j
@Service

public class GroupService {

    @Autowired
    private UserRepository UserRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

    public Group_tbl create_group(GroupDto dto, Long user_id) {

        //dto ->entity
        Group_tbl created = dto.toEntity();

        //그룹코드 랜덤 생성
        String group_code = Create_group_code();
        created.setGroup_code(group_code);
        //그룹에 user_id 추가

        User user = UserRepository.findById(user_id).orElse(null);
        created.setUser(user);

        return groupRepository.save(created);
    }

    private String Create_group_code() {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        System.out.println(generatedString);
        return generatedString;

    }
}