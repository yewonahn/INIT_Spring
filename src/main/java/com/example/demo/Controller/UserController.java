package com.example.demo.Controller;

import com.example.demo.Dto.UserDto;
import com.example.demo.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("회원가입 성공");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        return userService.loginUser(userDto)
                .map(user -> ResponseEntity.ok("로그인 성공. 안녕하세요," + user.getUsername() + "!"))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("아이디나 비밀번호가 잘못되었습니다."));
    }
}
