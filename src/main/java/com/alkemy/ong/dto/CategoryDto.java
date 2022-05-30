package com.alkemy.ong.dto;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

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

