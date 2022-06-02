package com.alkemy.ong.mapper;

import com.alkemy.ong.auth.dto.UserRequestDto;
import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.UserEntity;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Component
public class UserMapper {

    public UserEntity userDto2Entity(@NotNull UserRequestDto dto, boolean roles) {
        UserEntity entity = new UserEntity();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        if (roles) {
            entity.setRoles(dto.getRoles());
        }
        return entity;
    }

    public UserRequestDto userEntity2Dto(@NotNull UserEntity entity, boolean roles) {
        UserRequestDto dto = new UserRequestDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        if (roles)
            for (Role role : entity.getRoles()) {
                dto.getRoles().add(role);
            }
        return dto;
    }

    public UserResponseDto userEntity2ResponseDto(@NotNull UserEntity entity, boolean roles) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        if (roles) {
            dto.setRoles(entity.getRoles());
        }
        return dto;
    }

    public List<UserResponseDto> userEntityList2ResponseDtoList(@NotEmpty List<UserEntity> entities, boolean roles) {
        List<UserResponseDto> responseDtos = new ArrayList<>();
        for (UserEntity entity : entities) {
            responseDtos.add(this.userEntity2ResponseDto(entity, roles));
        }
        return responseDtos;
    }
}
