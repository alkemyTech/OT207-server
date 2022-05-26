package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter
@Setter
public class OrganizationDTO {

    private Long id;

    @NotNull(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Image cannot be empty")
    private String image;

    @Digits(integer = 10, fraction = 0)
    @Size(min = 9, max = 10)
    @NotEmpty(message = "Phone cannot be empty")
    private String phone;

    private String address;

}
