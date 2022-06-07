package com.alkemy.ong.service.impl;

import com.alkemy.ong.auth.dto.UserResponseDto;
import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.model.UserEntity;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
   private CategoryMapper categoryMapper;
    @Autowired
   private CategoryRepository categoryRepository;

    public CategoryDTO addCategory(CategoryDTO categoryDto) {
        try{
            Category CategoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category savedEntity = categoryRepository.save(CategoryEntity);
            return categoryMapper.categoryEntityToCategoryDto(savedEntity);}
        catch (Exception e){
            throw new ConflictException("There is already a category with this name " + categoryDto.getName());
        }
    }

    @Override
    public List<CategoryDtoName> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("The list is empty");
        }
        List<CategoryDtoName> allCategories = categoryMapper.CategoryListResponseDtoList(categories);

        return allCategories;
    }
}