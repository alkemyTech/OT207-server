package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.model.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public Category categoryDtoToCategoryEntity(CategoryDTO categoryDto) {

        Category categoryEntity = new Category();
        categoryEntity.setId(categoryDto.getId());
        categoryEntity.setName(categoryDto.getName());
        categoryEntity.setDescription(categoryDto.getDescription());
        categoryEntity.setImage(categoryDto.getImage());

        return categoryEntity;
    }

    public CategoryDTO categoryEntityToCategoryDto(Category savedEntity) {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setId(savedEntity.getId());
        categoryDTO.setName(savedEntity.getName());
        categoryDTO.setDescription(savedEntity.getDescription());
        categoryDTO.setImage(savedEntity.getImage());

        return categoryDTO;
    }
}
