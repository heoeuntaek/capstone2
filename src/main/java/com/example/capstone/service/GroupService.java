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
    private UserRepository userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;

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

    public Group_tbl create_group(GroupDto dto, String user_login_id) {

        //dto ->entity
        Group_tbl groupCreated = dto.toEntity();

        //그룹코드 랜덤 생성
        String group_code = Create_group_code();
        groupCreated.setGroup_code(group_code);
        Group_tbl group_saved = groupRepository.save(groupCreated);
        //그룹에 user_id 추가

        User targetUser = userRepository.findByUser_login_id(user_login_id);
        targetUser.setGroup_tbl(groupCreated);

        log.info("그룹생성 {}", groupCreated);
        log.info("유저정보 {}", targetUser);
        userRepository.save(targetUser);

        return group_saved;
    }

    public Group_tbl delete_group(Long group_id, String user_login_id) {
        User user_target = userRepository.findByuser_name(user_login_id);
        log.info("user_target{}",user_target);
        log.info("user_target.getGroup_tbl(){}",user_target.getGroup_tbl());
        log.info("user_target.getGroup_tbl().getId() {}",user_target.getGroup_tbl().getId());
//        log.info("user_target.getGroup_tbl().getId() {}",user_target.getGroup_tbl().getId().toString());

        userRepository.deleteGroupId(user_target.getGroup_tbl().getId().intValue());

        Group_tbl group_deleted = groupRepository.findById(group_id).get();

        groupRepository.deleteById(group_id);
        log.info("group삭제 완료",user_target.getGroup_tbl().getId().toString());
        return group_deleted;
    }

    public Group_tbl find_group_by_id(Long group_id) {
        return groupRepository.findById(group_id).get();
    }


    public Group_tbl find_group_by_code(String group_code) {
        return groupRepository.findByGroup_code(group_code);

    }
}