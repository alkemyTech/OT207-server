package com.alkemy.ong.auth.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;

@Getter
@Setter
public class UserUpdateDto {

    private String firstName;

    private String lastName;

    @Email
    private String email;
}
