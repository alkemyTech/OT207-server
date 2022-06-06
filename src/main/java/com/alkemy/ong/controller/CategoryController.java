package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO){
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> modifyCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDto) {
        CategoryDTO newCategory = categoryService.modifyCategory(categoryId, categoryDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCategory);
    }

}
