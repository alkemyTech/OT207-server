package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;

import java.util.List;


public interface ICategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDto);
    List<CategoryDtoName> getAllCategories();

}
