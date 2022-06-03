package com.alkemy.ong.dto;

import com.alkemy.ong.model.Organization;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlidesRequestDTO {
    private String text;
    private Integer orderSlides;
    private Organization organizations;
    private String base64Value;
}
