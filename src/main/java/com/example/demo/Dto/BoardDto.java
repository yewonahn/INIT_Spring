package com.example.demo.Dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Dto = 상자
// controller 에서, DB 에 넣을 새로운 데이터를 받아올 때, Dto 라는 상자에 담아오는 것
// ######## 실제 데이터베이스에 넣기 위해서 잠깐 담아두는 (거쳐가는) 상자의 느낌
public class BoardDto {
    private String title;
    private String content;
}
