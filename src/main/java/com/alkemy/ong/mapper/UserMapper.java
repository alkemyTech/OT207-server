package com.alkemy.ong.mapper;

import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.User;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Arrays;

@Component
public class UserMapper {

    public User UserDto2Entity(@NotNull UserRequestDto dto, boolean flag) {
        User entity = new User();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        if (flag) {
            for (Role role : dto.getRoles()) {
                entity.getRoles().add(role);
            }
//aca el for puede llegar a romper el programa
        }
        return entity;
    }

    public UserRequestDto UserEntity2Dto(@NotNull User entity) {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());

        for (Role role : entity.getRoles()) {
            dto.getRoles().add(role);
        }

        return dto;
    }

    public UserResponseDto UserEntity2ResponseDto(@NotNull User entity) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setRoles(entity.getRoles()); //el for era el problema

        return dto;
    }

}
