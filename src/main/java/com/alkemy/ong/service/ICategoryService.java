package com.alkemy.ong.service;

import com.alkemy.ong.dto.CategoryDto;
import org.springframework.stereotype.Service;

@Service
public interface ICategoryService {
    CategoryDto addCategory(CategoryDto categoryDto);
}
