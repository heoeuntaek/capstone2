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
        return userRepository.findById(id).orElse(null);  //객체 하나 반환
    }

    public User create(UserDto dto) {

        User created = dto.toEntity();

        if(created.getId() != null) {
            throw new IllegalArgumentException("이미 아이디가 있어서 생성할 수 없습니다.");
        }
        return userRepository.save(created);
    }
}