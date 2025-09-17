package com.grup_7.LibraryApp.dto.categoryDto.request;

public class CategoryUpdateDtoRequest {
    private String name;

    public CategoryUpdateDtoRequest() {}

    public CategoryUpdateDtoRequest(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}