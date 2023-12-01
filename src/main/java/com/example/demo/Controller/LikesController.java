package com.example.demo.Controller;

import com.example.demo.Dto.LikesDto;
import com.example.demo.Service.LikesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor    // 컨트롤러와 서비스의 의존성을 주입해주기 위해서
@RequestMapping("/api/likes")
public class LikesController {
    private final LikesService likeService;

    // 좋아요 등록
    @PostMapping("/")
    public ResponseEntity<String> insert(@RequestBody LikesDto likeDto) {
        likeService.createLike(likeDto);

        // 좋아요 기능 작동 잘 됐는지 리턴 받아야 하니까
        return ResponseEntity.status(HttpStatus.CREATED).body("좋아요 등록 성공");
    }

    // 좋아요 취소
    // 위에는 Post, 이거는 Delete 이므로 똑같은 주소 ("/") 사용해도 됨
    // 단, 다른 방식인 경우 같은 주소 사용하면 안됨
    @DeleteMapping("/")
    // delete 는 반환 값 없으므로 void
    public ResponseEntity<Void> delete(@RequestBody LikesDto likeDto) {
        likeService.deleteLike(likeDto);

        return ResponseEntity.noContent().build();
    }
}