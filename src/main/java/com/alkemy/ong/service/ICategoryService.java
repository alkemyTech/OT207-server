package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDTO;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService {
    CategoryDTO addCategory(CategoryDTO categoryDto);
    
    void deleteCategory(Long id);
}
