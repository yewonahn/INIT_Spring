package com.example.demo.Service;

import com.example.demo.Dto.LikesDto;
import com.example.demo.Entity.Board;
import com.example.demo.Entity.Likes;
import com.example.demo.Entity.User;
import com.example.demo.Repository.BoardRepository;
import com.example.demo.Repository.LikesRepository;
import com.example.demo.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
/*
<@RequiredArgsConstructor 써주는 이유>

객체가 3개니까, this. 다 해주려면 코드 길어짐
이거 쓰면, 생성자를 자동으로 주입해주고 서버스와 레포지토리를 의존성 있게 연결해줌

이거 안썼으면,
public BoardService (BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }
이 생성자를 Likes,User, Board 3개에 대해 다 만들어줬어야 함
 */
@RequiredArgsConstructor
public class LikesService {
    private final LikesRepository likesRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    // 좋아요 등록
    public void createLike(LikesDto likeDto) {
        // Optional 로 선언해줬었기 때문에, orElseThrow 이렇게 NullPointer 날려줘야 함
        // 없으면, NullPointerException 이런거 이용해서 "게시판이 없습니다" 이런 오류 알림창 띄우는 것 같은 기능을 구현할 수 있음
        User user = userRepository.findById(likeDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board = boardRepository.findById(likeDto.getBoardId()).orElseThrow(NullPointerException::new);

        // 해당 사용자가 해당 게시물에 이미 좋아요 눌렀었는지 확인
        if (likesRepository.findByUserAndBoard(user,board).isEmpty()) {
            Likes newLike = new Likes();
            newLike.setUser(user);
            newLike.setBoard(board);
            likesRepository.save(newLike);
        }
    }

    // 좋아요 삭제
    public void deleteLike(LikesDto likeDto) {
        User user = userRepository.findById(likeDto.getUserId()).orElseThrow(NullPointerException::new);
        Board board = boardRepository.findById(likeDto.getBoardId()).orElseThrow(NullPointerException::new);

        if (likesRepository.findByUserAndBoard(user, board).isPresent()) {
            likesRepository.deleteByUserAndBoard(user, board);
        }
    }
}
