package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.auth.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserResponseDto register(UserDto userDto) throws UsernameNotFoundException {

        if(userRepository.existsByEmail(userDto.getEmail())){
            throw new UsernameNotFoundException("There is already an account with this email" + userDto.getEmail());
        }

        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        Role role = roleRepository.findByName("ROLE_USER").get();
        User user = userMapper.UserDto2Entity(userDto);
        user.setRole(role);

        UserResponseDto userResponseDto = userMapper.UserEntity2ResponseDto(userRepository.save(user));

        return userResponseDto;
    }
}
