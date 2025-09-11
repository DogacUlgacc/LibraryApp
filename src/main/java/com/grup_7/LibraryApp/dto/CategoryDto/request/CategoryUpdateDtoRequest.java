package com.grup_7.LibraryApp.dto.CategoryDto.request;

public class CategoryUpdateDtoRequest {

    private String name;
    private int id;

    public CategoryUpdateDtoRequest(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public CategoryUpdateDtoRequest(){};

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}
}
