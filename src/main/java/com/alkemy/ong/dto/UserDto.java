package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "First name can not be null")
    private String firstName;

    @NotNull(message = "Last name can not be null")
    private String lastName;

    @NotNull(message = "Email can not be null")
    private String email;

    @NotNull(message = "Password can not be null")
    private String password;

    //@NotNull
    //private Role roleId;
}
