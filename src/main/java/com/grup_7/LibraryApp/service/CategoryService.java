package com.grup_7.LibraryApp.service;

import com.grup_7.LibraryApp.dto.CategoryDto.request.CategoryUpdateDtoRequest;
import com.grup_7.LibraryApp.dto.CategoryDto.request.CreateCategoryDtoRequest;
import com.grup_7.LibraryApp.dto.CategoryDto.response.CategoryUpdateDtoResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.CreatedCategoryResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.GetAllCategoriesDtoResponse;
import com.grup_7.LibraryApp.dto.CategoryDto.response.GetCategoryByIdDtoResponse;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<GetAllCategoriesDtoResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(category -> new GetAllCategoriesDtoResponse(
                        category.getName(),
                        category.getId()
                ))
                .toList();
    }

    public GetCategoryByIdDtoResponse getCategoryByIdDtoResponse(int id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        return new GetCategoryByIdDtoResponse(
                category.getName(),
                category.getId()
        );
    }
    public CreatedCategoryResponse addCategory(CreateCategoryDtoRequest request) {
        Category category = new Category();
        category.setName(request.getName());
        category.setId(request.getId());

        Category savedCategory = categoryRepository.save(category);


        return new CreatedCategoryResponse(
                savedCategory.getName(),
                savedCategory.getId()
        );
    }
    public CategoryUpdateDtoResponse updateCategory(int id, CategoryUpdateDtoRequest request) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category bulunamadı"));

        category.setName(request.getName());
        category.setId(request.getId());

        Category updatedCategory = categoryRepository.save(category);

        return new CategoryUpdateDtoResponse(
                updatedCategory.getName(),
                updatedCategory.getId()
        );
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }


}