package com.alkemy.ong.controller;

import com.alkemy.ong.dto.CategoryDTO;
import com.alkemy.ong.dto.CategoryDtoName;
import com.alkemy.ong.exception.BadRequestException;
import com.alkemy.ong.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @Operation(summary = "Add a new category to the database")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Create category",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid field",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content)})
    @PostMapping
    public ResponseEntity<CategoryDTO> addCategory(@Valid @RequestBody CategoryDTO categoryDTO, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        CategoryDTO savedCategory = categoryService.addCategory(categoryDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @Operation(summary = "Get all categories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Get all categories",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content)})
    @GetMapping
    public ResponseEntity<List<CategoryDtoName>> getAllCategories() {
        List<CategoryDtoName> categoriesDTOs = categoryService.getAllCategories();
        return ResponseEntity.ok().body(categoriesDTOs);
    }

    @Operation(summary = "Update category")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Update category",
                content = @Content),
        @ApiResponse(responseCode = "400", description = "Invalid field",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Invalid id supplied",
                content = @Content)})
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> modifyCategory(@PathVariable Long categoryId, @RequestBody CategoryDTO categoryDto) {
        CategoryDTO newCategory = categoryService.modifyCategory(categoryId, categoryDto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(newCategory);
    }

    @Operation(summary = "Delete a category by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Delete the category",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found",
                content = @Content)})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    @Operation(summary = "Get a category by its id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the category",
                content = @Content),
        @ApiResponse(responseCode = "404", description = "Category not found",
                content = @Content),
        @ApiResponse(responseCode = "403", description = "Invalid token or accessing with invalid role",
                content = @Content)})
    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryDTO(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));

    }

}
