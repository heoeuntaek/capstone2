package com.example.capstone.repository;

import com.example.capstone.entity.Group_tbl;
import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group_tbl, Long> {
}