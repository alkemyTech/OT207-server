package com.alkemy.ong.controller;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.dto.UserUpdateDto;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

@Autowired
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

    @PatchMapping("users/{id}")
    public UserResponseDto updateUser(
            @PathVariable(value = "id") Long userId, @Valid @RequestBody UserUpdateDto userUpdateDto, BindingResult bindingResult) throws Exception {
        if (bindingResult.hasErrors()) {
            throw new BadRequestException(bindingResult);
        }
        UserResponseDto userResponseDto = userService.updateUser(userId, userUpdateDto);
        return userResponseDto;
    }

}
