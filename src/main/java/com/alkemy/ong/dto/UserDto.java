package com.alkemy.ong.dto;

import com.alkemy.ong.model.Role;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {

    @NotNull(message = "First name cannot be null")
    private String firstName;

    @NotNull(message = "Last name cannot be null")
    private String lastName;

    @NotNull(message = "Email cannot be null")
    private String email;

    @NotNull(message = "Password cannot be null")
    private String password;

    @NotNull(message = "Role cannot be null")
    private Role roleId;
}
