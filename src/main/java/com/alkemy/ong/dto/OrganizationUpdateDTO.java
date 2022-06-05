package com.alkemy.ong.dto;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrganizationUpdateDTO {
    
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String name;
   
    @NotEmpty(message = "Image cannot be empty")
    private String image;
    
    @NotEmpty(message = "Address cannot be empty")
    private String address;

    @Digits(integer = 10, fraction = 0)
    @Size(min = 9, max = 10)
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;
    
    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;
    
    @NotEmpty(message = "Welcome text cannot be empty")
    private String welcomeText;

    @NotEmpty(message = "About text cannot be empty")
    private String aboutUsText;
}
