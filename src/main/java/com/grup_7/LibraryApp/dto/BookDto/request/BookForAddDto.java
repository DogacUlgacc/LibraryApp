package com.grup_7.LibraryApp.dto.BookDto.request;

import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;

import java.util.List;


public class BookForAddDto {

    private String title;
    private List<Author> authors;
    private int publishYear;
    private Category category;
    private int totalCopies;
    private Publisher publisher;

    public BookForAddDto(String title, List<Author> authors, int publishYear, Category category, int totalCopies, Publisher publisher) {
        this.title = title;
        this.authors = authors;
        this.publishYear = publishYear;
        this.category = category;
        this.totalCopies = totalCopies;
        this.publisher = publisher;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}