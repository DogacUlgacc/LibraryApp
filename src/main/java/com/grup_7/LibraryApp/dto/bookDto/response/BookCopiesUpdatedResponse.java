package com.grup_7.LibraryApp.dto.bookDto.response;

import java.util.List;

public class BookCopiesUpdatedResponse {
    private String title;
    private List<BookAuthorDto> authors;
    private Integer availableCopies;
    private Integer totalCopies;

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }

    private BookCategoryDto category;
    private BookPublisherDto publisher;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<BookAuthorDto> getAuthors() {
        return authors;
    }

    public void setAuthors(List<BookAuthorDto> authors) {
        this.authors = authors;
    }

    public Integer getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(Integer availableCopies) {
        this.availableCopies = availableCopies;
    }

    public BookCategoryDto getCategory() {
        return category;
    }

    public void setCategory(BookCategoryDto category) {
        this.category = category;
    }

    public BookPublisherDto getPublisher() {
        return publisher;
    }

    public void setPublisher(BookPublisherDto publisher) {
        this.publisher = publisher;
    }
}
