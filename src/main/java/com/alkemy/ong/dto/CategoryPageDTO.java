package com.alkemy.ong.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategoryPageDTO {
    private String next;
    private String current;
    private String previous;
    private List<CategoryDTO> categoryList;
}
