package com.grup_7.LibraryApp.dto.CategoryDto.request;

public class CreateCategoryDtoRequest {

    private String name;
    private int id;

    public CreateCategoryDtoRequest(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public CreateCategoryDtoRequest(){};

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}
}
