package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {


    // entity의 역할은 따로 mySql 들어가서 create 이런거 해줄 필요 없게 여기서 해주는 것
    // DB table 속성 정보들을 여기에 작성해주면 됨
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

}