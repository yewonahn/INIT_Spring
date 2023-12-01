package com.example.demo.Controller;

import com.example.demo.Dto.BoardDto;
import com.example.demo.Entity.Board;
import com.example.demo.Service.BoardService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController

@RequestMapping("/api/boards")
public class BoardController {
    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    // get 은 *특정* 값을 가져오는 게 아닌, 그냥 전체를 가져오기만 하면 될 때 사용
    @GetMapping
    public List<Board> getAllBoards() {
        return boardService.getAllBoards();
    }

    /*
    service 에서, 레포지토리에 있는 거 사용
    controller 에서, service 에 있는 거 사용
    (controller 최대한 간결하게 적는게 좋으니까, controller 너무 길어지지 않게하기 위해서)
     */

    /*
    "{/id}" 는 pathVariable 랑 관련이 있는데, 일종의 변수라고 생각하면 됨
    Long id 여기에 들어있는 게, "{/id}" 여기 url 에 들어가게 됨
     */
    // [Read] 특정 게시글 조회
    @GetMapping("{/id}")
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> board = boardService.getBoardById(id);

        // optional 이었으므로 map, 삼항연산자 써 줌
        // id 값 있는 경우에만 -> map 호출돼서 ResponseEntity::ok 를 넣어주고 map 호출
        // id 값 없는 경우 -> orElseGet(() -> ResponseEntity.notFound().build() 호출
        // 즉, 비어있는 경우 404 notFound 페이지 호출
        return board.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /*
    <Get vs Post>
    DB 에 넣을 때 post
    DB 에서 가져올 때 get
     */
    // [Create] 새로운 게시글 작성
    @PostMapping
    public ResponseEntity<Board> createBoard(@RequestBody BoardDto boardDto) {
        Board newBoard = boardService.createBoard(boardDto);
        return ResponseEntity.ok(newBoard);
    }

    // 1. 찾아서 2. 넣어줘야 하니까
    // 위에 두개가 합쳐진 거라고 생각
    // [Update]
    @PutMapping("/{id}")
    public ResponseEntity<Board> updateBoard(@PathVariable Long id, @RequestBody BoardDto boardDto) {
        Board updateBoard = boardService.updateBoard(id, boardDto);

        // null 이 아니어야 수정이 됐다는거니까 != 써줘야 함
        return updateBoard != null ? ResponseEntity.ok(updateBoard) : ResponseEntity.notFound().build();
    }

    // [Delete]
    @DeleteMapping("{/id}")
    public ResponseEntity<Board> deleteBoard(@PathVariable Long id) {
        boardService.deleteBoard(id);
        return ResponseEntity.noContent().build();
        // noContent() -> 응답 데이터가 없다는 걸 알려줌
    }
}
