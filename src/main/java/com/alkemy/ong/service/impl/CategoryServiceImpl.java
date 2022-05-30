package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDto;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
   private CategoryMapper categoryMapper;
    @Autowired
   private CategoryRepository categoryRepository;

    public CategoryDto addCategory(CategoryDto categoryDto) {
        try{
            Category CategoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category savedEntity = categoryRepository.save(CategoryEntity);
            return categoryMapper.categoryEntityToCategoryDto(savedEntity);}
        catch (Exception e){
            throw new ConflictException(e.getMessage());
        }
    }
}