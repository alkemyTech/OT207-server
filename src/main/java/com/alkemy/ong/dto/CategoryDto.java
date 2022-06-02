package com.alkemy.ong.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CategoryDto {
    @NotBlank(message = "Name cannot be empty")
    @NotEmpty(message = "Name cannot be null")
    @Pattern(regexp = "[a-zA-Z\\s]*", message = "Name cannot contain numbers or characters other than letters")
    private String name;

    private String description;

    private String image;
}

