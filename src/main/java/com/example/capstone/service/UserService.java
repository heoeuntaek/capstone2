package com.example.capstone.service;

import com.example.capstone.dto.UserDto;
import com.example.capstone.entity.User;
import com.example.capstone.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;




    public List<User> findAll() {
        return userRepository.findAll();  //리스트 형태로 반환
    }


    public User findOne(Long id) {
        log.info("정보 {}", userRepository.findById(id));
        return userRepository.findById(id).orElse(null);  //객체 하나 반환

    }

    public User create(UserDto dto) {

        User created = dto.toEntity();
        if(created.getId() != null) {
            throw new IllegalArgumentException("이미 아이디가 있어서 생성할 수 없습니다.");
        }
        return userRepository.save(created);
    }


    public User update(Long id, UserDto dto) {
        //1: 수정용 엔티티 조회 dto -> entity
        User user = dto.toEntity();
        log.info("id : {}, article : {}", id, user.toString());

        // 2:대상 엔티티 조회
        User target = userRepository.findById(id).orElse(null);  //기존에 있던 것

        //3 : 잘못된 요청 처리(id 없거나, id 다른경우

        if (target == null || id != user.getId()) {
            // 400 - 잘못된 요청
            log.info("잘못된 요청! id : {}, article : {}", id, user.toString());
            return null;
        }
        // 4 : 업데이트 및 정상 응답(200)
        target.patch(user); // 기존에 있던것을 붙임
        User updated = userRepository.save(target); //target 저장, target을 업데이트
        return updated;

    }


}