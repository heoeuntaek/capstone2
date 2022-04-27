package com.example.capstone.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor  //생성자
@NoArgsConstructor  //default생성자 추가

@ToString
@Getter
@Table (name = "schedule")
@Entity  //DB가 해당 객체 인식 가능   (해당 클래스로 테이블을 만듬)
public class Schedule {
    @Id  //대표값 like 주민번호
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 생성
    private Long id;

    @Column(columnDefinition = "json")
    private String schedul_data;

    @Column
    private String schedul_name;


    @ManyToOne //해당 댓글 엔티티 여러개가, 다대일관계
    @JoinColumn(name = "userId") //  테이블에서 대상정보의 colum
    private User user;


}