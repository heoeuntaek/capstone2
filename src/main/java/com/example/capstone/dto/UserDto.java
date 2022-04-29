package com.example.capstone.dto;

import com.example.capstone.entity.User;
import lombok.*;


@Getter
@ToString
@Setter
@NoArgsConstructor

public class UserDto {

    private Long id;
    private String user_login_id;
    private String user_pass;
    private String user_name;

    private Long group_id;

    public UserDto(Long id, String user_login_id, String user_pass, String user_name, Long group_id) {
        this.id = id;
        this.user_login_id = user_login_id;
        this.user_pass = user_pass;
        this.user_name = user_name;
        this.group_id = group_id;
    }

    public static UserDto toDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUser_login_id(),
                user.getUser_pass(),
                user.getUser_name(),
                user.getGroup_tbl().getId()
        );

    }

    public User toEntity() {
        return new User(id, user_login_id, user_pass, user_name, null, null);
    }



}