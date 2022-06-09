package com.alkemy.ong.controller;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {


    private IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> usersDTOs = this.userService.getAllUsers();
        return ResponseEntity.ok().body(usersDTOs);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") Long userId) {

        this.userService.deleteUserById(userId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
