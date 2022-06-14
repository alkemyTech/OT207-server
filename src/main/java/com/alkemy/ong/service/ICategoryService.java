package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDto);
  
    List<CategoryDtoName> getAllCategories();

    Page<CategoryDTO> getAllCategoriesPageable(Pageable page);
  
    CategoryDTO getCategoryById(Long id);
  
    void deleteCategory(Long id);
  
    CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto);

}
