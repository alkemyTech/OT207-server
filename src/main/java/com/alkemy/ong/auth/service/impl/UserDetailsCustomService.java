package com.alkemy.ong.auth.service.impl;

import com.alkemy.ong.auth.config.SecurityConfiguration;

import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.enums.RolName;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.UserEntity;
import com.alkemy.ong.repository.RoleRepository;
import com.alkemy.ong.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        Optional<UserEntity> userEntity = userRepository.findByEmail(userName);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("The username or password is incorrect");
        }
        List<GrantedAuthority> roles = new ArrayList<>();
        for (Role role : userEntity.get().getRoles()) {
            roles.add(new SimpleGrantedAuthority(role.getName()));
        }
        UserEntity user = userEntity.get();
        return new User(user.getUsername(), user.getPassword(), user.isEnabled(), user.isAccountNonExpired(), user.isCredentialsNonExpired(), user.isAccountNonLocked(), roles);
    }


    @Transactional()
    public boolean save(UserRequestDto userRequestDto) throws Exception {
        String encrypted = securityConfiguration.passwordEncoder().encode(userRequestDto.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userRequestDto.getEmail());
        userEntity.setPassword(encrypted);
        UserEntity userEntityResult;
        userEntityResult = this.userRepository.save(userEntity);
        return userEntityResult != null;
    }

    @Transactional()
    public UserResponseDto register(UserRequestDto userRequestDto) throws Exception {

        Optional<UserEntity> userEntity = userRepository.findByEmail(userRequestDto.getEmail());
        if (userEntity.isPresent()) {
            throw new ConflictException("There is already an account with this email " + userRequestDto.getEmail());
        }
        UserEntity user;
        user = userMapper.userDto2Entity(userRequestDto);
        user.setPassword(bCryptPasswordEncoder.encode(userRequestDto.getPassword()));
        Role role = roleRepository.findByName(RolName.ROLE_USER.toString()).get();
        user.setRoles(Arrays.asList(role));
        UserEntity result = userRepository.save(user);
        UserResponseDto userResponseDto = userMapper.userEntity2ResponseDto(result);

        return userResponseDto;
    }

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
