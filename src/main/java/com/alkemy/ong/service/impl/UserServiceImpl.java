package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.auth.repository.UserRepository;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.UserEntity;
import com.alkemy.ong.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRepository userRepository;


    @Transactional(readOnly = true)
    public List<UserResponseDto> getAllUsers() {
        List<UserEntity> entities = this.userRepository.findAll();
        if (entities.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<UserResponseDto> userResponseDtos = userMapper.userEntityList2ResponseDtoList(entities);
        return userResponseDtos;
    }

    public void deleteUserById(Long userId)  {
        Optional<UserEntity> user = this.userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new NotFoundException("the user does not exist or has already been deleted");
        }
        user.get().setDeleted(true);
        userRepository.save(user.get());
    }


}
