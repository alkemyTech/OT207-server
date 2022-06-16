package com.alkemy.ong.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class MemberDTO {

    private Long id;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull
    private String image;

    @Nullable
    private String facebookUrl;

    @Nullable
    private String instagramUrl;

    @Nullable
    private String linkedinUrl;

    @Nullable
    private String description;

    @Nullable
    private String image;

}
