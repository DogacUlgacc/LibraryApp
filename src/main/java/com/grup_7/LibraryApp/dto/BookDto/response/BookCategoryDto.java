package com.grup_7.LibraryApp.dto.BookDto.response;

public class BookCategoryDto {
    private String name;

    public BookCategoryDto() { }

    public BookCategoryDto(String name) { this.name = name; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
