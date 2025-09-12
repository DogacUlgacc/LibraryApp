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
                .map(c -> new GetAllCategoriesDtoResponse(c.getName(), c.getId()))
                .toList();
    }

    public GetCategoryByIdDtoResponse getCategoryByIdDtoResponse(int id) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));
        return new GetCategoryByIdDtoResponse(c.getName(), c.getId());
    }

    public CreatedCategoryResponse addCategory(CreateCategoryDtoRequest request) {
        Category c = new Category();
        c.setName(request.getName());
        Category saved = categoryRepository.save(c);

        return new CreatedCategoryResponse(saved.getName(), saved.getId());
    }

    public CategoryUpdateDtoResponse updateCategory(int id, CategoryUpdateDtoRequest request) {
        Category c = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategori bulunamadı"));

        c.setName(request.getName());
        Category updated = categoryRepository.save(c);

        return new CategoryUpdateDtoResponse(updated.getName(), updated.getId());
    }

    public void delete(int id) {
        categoryRepository.deleteById(id);
    }
}