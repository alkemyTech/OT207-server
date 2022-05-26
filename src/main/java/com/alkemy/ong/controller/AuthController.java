package com.alkemy.ong.controller;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.dto.UserResponseDto;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService iUserService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> userRegistration(@RequestBody UserDto userDto){
        if(userRepository.existsByEmail(userDto.getEmail())){
            return new ResponseEntity<>("There is already an account with this email" + userDto.getEmail(), HttpStatus.BAD_REQUEST);
        }
        UserResponseDto userResponseDto = iUserService.register(userDto);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }
}
