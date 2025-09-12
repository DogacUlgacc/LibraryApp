package com.grup_7.LibraryApp.dto.CategoryDto.response;

public class GetCategoryByIdDtoResponse {

    private String name;
    private int id;

    public GetCategoryByIdDtoResponse(String name, Integer id) {
        this.name = name;
        this.id = id;
    }
    public GetCategoryByIdDtoResponse(){};

    public String getName() { return name;}
    public void setName(String name) { this.name = name;}

    public Integer getId() { return id;}
    public void setId(Integer id) { this.id = id;}

}