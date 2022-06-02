package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.dto.UserResponseDto;

import java.util.List;

public interface IUserService {

    List<UserResponseDto> getAllUsers();
}
