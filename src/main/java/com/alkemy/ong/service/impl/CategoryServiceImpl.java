package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import com.amazonaws.services.sns.model.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDto) {
        try{
            Category CategoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category savedEntity = categoryRepository.save(CategoryEntity);
            return categoryMapper.categoryEntityToCategoryDto(savedEntity);}
        catch (Exception e){
            throw new ConflictException("There is already a category with this name " + categoryDto.getName());
        }
    }

    public CategoryDTO getCategoryById(Long id) {
        Category categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryMapper.categoryEntityToCategoryDto(categoryEntity);
    }
}
    @Override
    public void deleteCategory(Long id) throws NotFoundException {
        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            throw new NotFoundException("Category id does not exist");
        }

    }
}
