package com.grup_7.LibraryApp.dto.bookDto.response;

import java.util.List;

public class GetAllBooksDtoResponse {
    private String title;
    private List<BookAuthorDto> authors;
    private Integer availableCopies;
    private BookCategoryDto category;
    private BookPublisherDto publisher;

    public GetAllBooksDtoResponse() { }

    public GetAllBooksDtoResponse(String title,
                                  List<BookAuthorDto> authors,
                                  Integer availableCopies,
                                  BookCategoryDto category,
                                  BookPublisherDto publisher) {
        this.title = title;
        this.authors = authors;
        this.availableCopies = availableCopies;
        this.category = category;
        this.publisher = publisher;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public List<BookAuthorDto> getAuthors() { return authors; }
    public void setAuthors(List<BookAuthorDto> authors) { this.authors = authors; }

    public Integer getAvailableCopies() { return availableCopies; }
    public void setAvailableCopies(Integer availableCopies) { this.availableCopies = availableCopies; }

    public BookCategoryDto getCategory() { return category; }
    public void setCategory(BookCategoryDto category) { this.category = category; }

    public BookPublisherDto getPublisher() { return publisher; }
    public void setPublisher(BookPublisherDto publisher) { this.publisher = publisher; }
}
