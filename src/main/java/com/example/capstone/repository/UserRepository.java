package com.example.capstone.repository;

import com.example.capstone.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface UserRepository extends CrudRepository<User, Long>  {


    //Iterable<Article> findAll(); // 메소드 오버라이딩
    ArrayList<User> findAll(); //위에것을 아래걸로 바꾸기
}