package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;    // import persistence 인지 확인해주기

// entity 라는걸 명시해주기 위해서
@Entity
@Getter
@Setter
public class Board {

    @Id // 기본키인거 알려주기 위해
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 아이디가 1씩 증가하도록 auto increment
    private Long id;
    // <Long 타입 사용한 이유>
    // Long 타입은 일반적으로 DB 에서 사용되는 기본 키 타입 중 하나
    // 많은 DB 에서 기본 키가 숫자형이고, 자동으로 증가하는 식별자가 필요한데
    // Long 은 큰 범위의 정수를 다룰 수 있고, 많은 레코드를 처리하는 데 적합한 타입

    private String title;
    private String content;
}
