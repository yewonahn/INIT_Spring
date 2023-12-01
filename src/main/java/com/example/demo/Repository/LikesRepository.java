package com.example.demo.Repository;

import com.example.demo.Entity.Board;
import com.example.demo.Entity.Likes;
import com.example.demo.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/*
update, delete 처럼 트랜젝션이 깨질 수 있는 기능의 경우,
@Transactional 써줘야 함

지금까지는 find 만 했었기 때문에 문제 생길일이 없었음
이번 좋아요 기능 구현에서는, 삭제 기능이 쓰였음
user, board 레포지토리도 가져와서 있는지 확인하고, 그걸 이용했으므로,
이번엔 @Transactional 써줘야 함
 */
@Transactional
public interface LikesRepository extends JpaRepository<Likes, Long> {
    // 해당하는 user, board 가 실제로 있는지 확인하기 위해서
    Optional<Object> findByUserAndBoard(User user, Board board);

    void deleteByUserAndBoard(User user, Board board);
}
