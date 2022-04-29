package com.example.capstone.repository;

import com.example.capstone.entity.Group_tbl;
import com.example.capstone.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GroupRepository extends CrudRepository<Group_tbl, Long> {

    @Query(value =
            "select * " +
                    "from group_tbl " +
                    "where group_code = :group_code",
            nativeQuery = true) //true로 해줘야 해당sql 동작
    Group_tbl findByGroup_code(@Param("group_code")String group_code);



}