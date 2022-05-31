package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.config.SecurityConfiguration;

import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserMapper userMapper;


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<User> userEntity = userRepository.findByEmail(userName);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("The username or password is incorrect");
        }
        User user = userEntity.get();
        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    @Transactional()
    public boolean save(UserRequestDto userRequestDto) throws Exception {
        String encrypted = securityConfiguration.passwordEncoder().encode(userRequestDto.getPassword());
        User userEntity = new User();
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(encrypted);
        User userResult;
        userResult = this.userRepository.save(userEntity);
        return userResult != null;
    }

    //@Transactional()
    public UserResponseDto register(UserRequestDto userRequestDto) throws Exception {

        Optional<User> userEntity = userRepository.findByEmail(userRequestDto.getEmail());
        if (userEntity.isPresent()) {
            throw new ConflictException("There is already an account with this email " + userRequestDto.getEmail());
        }
        User user;
        user = userMapper.UserDto2Entity(userRequestDto, false);

        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));

        Role role = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Arrays.asList(role));

        User result = userRepository.save(user);

        UserResponseDto userResponseDto = userMapper.UserEntity2ResponseDto(result);

        return userResponseDto;
    }
}
