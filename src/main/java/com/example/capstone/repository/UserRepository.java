package com.example.capstone.repository;

import com.example.capstone.entity.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface UserRepository extends CrudRepository<User, Long>  {


    //Iterable<Article> findAll(); // 메소드 오버라이딩
    ArrayList<User> findAll(); //위에것을 아래걸로 바꾸기

    @Query(value =
            "select * " +
                    "from user " +
                    "where user_login_id = :user_login_id",
            nativeQuery = true) //true로 해줘야 해당sql 동작
    User findByUser_login_id(@Param("user_login_id")String user_login_id);


    @Query(value =
            "select * " +
                    "from user " +
                    "where user_name = :user_name",
            nativeQuery = true) //true로 해줘야 해당sql 동작
    User findByuser_name(@Param("user_name")String user_name);

    @Transactional
    @Modifying
    @Query(value =
            "update user " +
                    "set group_id = null " +
                    "where group_id = :group_id ",
            nativeQuery = true) //true로 해줘야 해당sql 동작
    void deleteGroupId(@Param("group_id") int group_id);

    @Query(value = "select * from user where group_id = :group_id", nativeQuery = true)
    List<User> findByGroup_id(@Param("group_id")Long group_id);
}