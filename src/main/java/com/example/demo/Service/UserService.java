package com.example.demo.Service;

import com.example.demo.Dto.UserDto;
import com.example.demo.Entity.User;
import com.example.demo.Repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User registerUser(UserDto userDto) {
        // 비밀번호 암호화
        String encryptedPassword = BCrypt.hashpw(userDto.getPassword(), BCrypt.gensalt());;

        // User 엔터티 생성 및 저장
        User newUser = new User();
        newUser.setUsername(userDto.getUsername());
        newUser.setPassword(encryptedPassword);
        return userRepository.save(newUser);
    }

    public Optional<User> loginUser(UserDto userDto) {
        Optional<User> userOptional = userRepository.findByUsername(userDto.getUsername());

        // 사용자가 존재하고, 비밀번호가 일치하면 사용자 반환
        return userOptional.filter(user -> checkPassword(userDto.getPassword(), user.getPassword()));
    }

    public boolean checkPassword(String plainPassword, String hashedPassword) {
        // BCrypt.checkpw 메서드를 사용하여 비밀번호 검증
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
}