package com.example.capstone.practice;

import lombok.*;

import javax.persistence.*;


@AllArgsConstructor  //생성자
@NoArgsConstructor  //default생성자 추가
@Entity
@Getter
@Setter
public class Member {

    @Id @GeneratedValue
    private Long id;    private String member_id;
    private String member_name;

    @ManyToOne
    @JoinColumn(name="team_id")
    private Team team;

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", member_name='" + member_name + '\'' +
                ", team=" + team +
                '}';
    }
}