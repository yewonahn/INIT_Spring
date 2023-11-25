package com.example.demo.Service;

import com.example.demo.Dto.BoardDto;
import com.example.demo.Entity.Board;
import com.example.demo.Repository.BoardRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service 인거 알려주기 위해서
@Service
public class BoardService {
    private final BoardRepository boardRepository;
    public BoardService (BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    /*
    <optional>
    값이 있을 수도 있고 없을 수도 있는 컨테이너 객체
    메서드가 특정 조건에 따라서 null 을 반환하는 경우, 이로 인해 NullPointerException 이 발생할 수 있는데,
    Optional 은 이러한 상황에서 명시적으로 값이 없는 상태를 나타내기 위해 사용됨

    Optional<String> optional = Optional.of("안녕");
    이런 식으로 사용

    if (optional.isPresent()) {
    }
    있는지 없는지 확인하기 위해 사용

    String get = optional.orElse("안녕!");
    이  값이 존재하면 안녕을, 존재하지 않으면 안녕! 을 return 가능
    즉, 값이 없을 때의 return 값 설정도 가능 -> 확장성이 많음
    */
    public Optional<Board> getBoardById(Long id) {
        return boardRepository.findById(id);
    }

    // [Create] 새로운 게시글 작성하는 함수
    public Board createBoard(BoardDto boardDto) {
        // 빈 보드 객체 만들기
        Board newBoard = new Board();

        newBoard.setTitle(boardDto.getTitle());
        newBoard.setContent(boardDto.getContent());

        // 이제 실제로 데이터를 넣어줘야 하니까
        return boardRepository.save(newBoard);
    }

    // [Update]
    // create 와의 차이점 : update 하려면 id를 알아야 함 -> id 를 받아와야 함 + Optional 사용
    // boardDto 여기에 수정해야 할 내용이 담겨있음
    public Board updateBoard(Long id, BoardDto boardDto) {

        // Optional<Board> 은 Board 타입의 객체를 감싸고 있거나, 값이 없을 때는 비어있는 'empty' Optional 객체를 나타냄
        // findByID 메서드는 주어진 ID에 해당하는 엔티티를 찾아서 Optional 로 감싼 상태로 반환해 줌
        // 이를 통해 해당 Id에 해당하는 엔티티가 존재하지 않을 경우에도 명시적으로 처리할 수 있음
        Optional<Board> optionalBoard = boardRepository.findById(id);

        // 명시적으로 null 체크를 하기 위해서
        if (optionalBoard.isPresent()) {
            Board existingBoard = optionalBoard.get();

            existingBoard.setTitle(boardDto.getTitle());
            existingBoard.setContent(boardDto.getContent());

            return boardRepository.save(existingBoard);
        }
        return null;
    }

    // [Delete]
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}
