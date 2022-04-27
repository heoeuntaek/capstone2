package com.example.capstone.practice;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
@ToString
@Getter
@Setter
public class Team {

    @Id
    @GeneratedValue
    @Column(name = "team_id")
    private String id;
    private String team_name;


    @OneToMany(mappedBy = "team")
    private List<Member> members= new ArrayList<>();

}