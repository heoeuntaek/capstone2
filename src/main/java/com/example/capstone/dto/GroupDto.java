package com.example.capstone.dto;

import com.example.capstone.entity.Group_tbl;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
public class GroupDto {
    private Long id;
    private String group_code;
    private String group_name;
    private String matched_schedule;

    public Group_tbl toEntity() {
        return new Group_tbl(id, group_code, group_name, null);
    }
}