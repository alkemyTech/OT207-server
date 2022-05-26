package com.alkemy.ong.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class TestimonialDto {

    @NotNull(message = "Name cannot be null")
    @Column(name = "name")
    private String name;

    @Nullable
    @Column(name = "image")
    private String image;

    @Nullable
    @Column(name = "content")
    private String content;

}
