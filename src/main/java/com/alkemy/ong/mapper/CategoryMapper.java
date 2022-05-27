package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category categoryDtoToCategoryEntity(CategoryDto categoryDto){

        Category categoryEntity = new Category();
        categoryEntity.setName(categoryDto.getName());
        return categoryEntity;
    }

    public CategoryDto categoryEntityToCategoryDto(Category savedEntity) {
        CategoryDto categoryDTO = new CategoryDto();
        categoryDTO.setName(savedEntity.getName());
        return categoryDTO;
    }
}
