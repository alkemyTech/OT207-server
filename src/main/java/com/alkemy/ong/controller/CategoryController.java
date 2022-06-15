package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.dto.CategoryPageDTO;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.mapper.CategoryMapper;
import com.alkemy.ong.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private CategoryMapper categoryMapper;

    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDtoName>> getAllCategories() {
        List<CategoryDtoName> categoriesDTOs = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoriesDTOs);
    }

    @GetMapping("/page")
    public ResponseEntity<?> getAllCategoriesPageable(@RequestParam(name ="page", defaultValue = "0") Integer page) {
        CategoryPageDTO categoryPage = categoryService.getAllCategoriesPageable(page);
        return ResponseEntity.ok(categoryPage);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> modifyCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDto) {
        CategoryDTO newCategory = categoryService.modifyCategory(categoryId, categoryDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));

    }

}
