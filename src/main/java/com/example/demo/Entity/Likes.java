package com.example.demo.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// mySql like 와 이름이 겹치므로 likes 사용
@Entity
@Getter
@Setter
public class Likes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    private Long likeId;

    // 게시판과 좋아요는 1:N 관계 (한 게시판에 좋아요 여러 개 가능하니까)
    @ManyToOne(targetEntity = Board.class)
    // 좋아요 테이블에서의 컬럼명 (참조하는 곳에서 이 이름으로 쓰겠다)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(targetEntity = User.class)
    @JoinColumn(name = "user_id")
    private User user;
}

