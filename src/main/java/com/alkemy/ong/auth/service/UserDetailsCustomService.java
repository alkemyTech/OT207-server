package com.alkemy.ong.auth.service;

import com.alkemy.ong.auth.config.SecurityConfiguration;

import com.alkemy.ong.dto.UserDto;
import com.alkemy.ong.model.User;
import com.alkemy.ong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional <User> userEntity = userRepository.findByEmail(userName);
        if (!userEntity.isPresent()) {
            throw new UsernameNotFoundException("The username or password is incorrect");
        }
        User user = userEntity.get();

        return new User(user.getEmail(), user.getPassword(), Collections.emptyList());
    }

    @Transactional()
    public boolean save(UserDto userDto) throws Exception {
        String encriptada = securityConfiguration.passwordEncoder().encode(userDto.getPassword());
        User userEntity = new User();
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(encriptada);
        User userResult;
        userResult = this.userRepository.save(userEntity);
        return userResult != null;

    }
}