package com.grup_7.LibraryApp.dto.CategoryDto.response;

public class CategoryUpdateDtoResponse {

    private String name;
    private int id;

    public CategoryUpdateDtoResponse(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public CategoryUpdateDtoResponse(){};

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}

}
