package com.grup_7.LibraryApp.controller;

import com.grup_7.LibraryApp.dto.CategoryDto.request.CategoryUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.CategoryDto.request.CreateCategoryDtoRequest;
import com.grup_7.LibraryApp.dto.CategoryDto.response.CategoryUpdateDtoResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.CreatedCategoryResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.GetAllCategoriesDtoResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.GetCategoryByIdDtoResponse;
import com.grup_7.LibraryApp.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<GetAllCategoriesDtoResponse> getAllPCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public GetCategoryByIdDtoResponse getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryByIdDtoResponse(id);
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CreatedCategoryResponse createCategory(@RequestBody CreateCategoryDtoRequest request)
    {
        return categoryService.addCategory(request);
    }
    @PutMapping("/{id}")
    public CategoryUpdateDtoResponse updateCategory(@PathVariable int id, @RequestBody CategoryUpdateDtoRequest request) {
        return categoryService.updateCategory(id, request);
    }

    @DeleteMapping("/{id}")
    public void deleteCategory(@PathVariable int id) {
        categoryService.delete(id);
    }
}
