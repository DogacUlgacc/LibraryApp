package com.grup_7.LibraryApp.dto.categoryDto.request;


public class CreateCategoryDtoRequest {
    private String name;

    public CreateCategoryDtoRequest() {}

    public CreateCategoryDtoRequest(String name) {
        this.name = name;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}