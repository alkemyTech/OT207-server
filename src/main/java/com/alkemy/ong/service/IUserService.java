package com.alkemy.ong.service;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.UserEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public interface IUserService {

    List<UserResponseDto> getAllUsers();

    void deleteUserById(Long userId);

}
