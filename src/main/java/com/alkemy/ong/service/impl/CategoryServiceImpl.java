package com.alkemy.ong.service.impl;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.exception.ConflictException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.model.Category;
import com.alkemy.ong.repository.CategoryRepository;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoryDTO addCategory(CategoryDTO categoryDto) {
        try {
            Category CategoryEntity = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category savedEntity = categoryRepository.save(CategoryEntity);
            return categoryMapper.categoryEntityToCategoryDto(savedEntity);
        } catch (Exception e) {
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

    public CategoryDTO getCategoryById(Long id) {
        Category categoryEntity = categoryRepository.findById(id).orElseThrow(() -> new NotFoundException("Category not found"));
        return categoryMapper.categoryEntityToCategoryDto(categoryEntity);
    }

    @Override
    public CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto) {

        if (categoryRepository.existsById(categoryId)) {

            Category category = categoryRepository.getById(categoryId);
            category = categoryMapper.categoryDtoToCategoryEntity(categoryDto);
            Category result = categoryRepository.save(category);
            CategoryDTO newCategoryDto = categoryMapper.categoryEntityToCategoryDto(result);

            return newCategoryDto;

        } else {
            throw new NotFoundException("Category id not found");
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


