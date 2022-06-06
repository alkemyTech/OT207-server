package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDto);

    CategoryDTO modifyCategory(Long categoryId, CategoryDTO categoryDto);
}
