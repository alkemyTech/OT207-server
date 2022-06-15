package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.CategoryPageDTO;
import com.alkemy.ong.model.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ICategoryService {

    CategoryDTO addCategory(CategoryDTO categoryDto);
  
    List<CategoryDtoName> getAllCategories();

    CategoryPageDTO getAllCategoriesPageable(Integer page);

    CategoryDTO getCategoryById(Long id);
  
    void deleteCategory(Long id);
  
    CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto);

}
