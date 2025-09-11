package com.grup_7.LibraryApp.dto.BookDto.response;

import com.grup_7.LibraryApp.entity.Author;
import com.grup_7.LibraryApp.entity.Category;
import com.grup_7.LibraryApp.entity.Publisher;

import java.util.List;

public class CreatedBookResponse {

    private String title;
    private List<Author> authors;
    private int availableCopies;
    private Category category;
    private Publisher publisher;


    public CreatedBookResponse(String title, List<Author> authors, int publishYear, Category category, int totalCopies, Publisher publisher) {}


    public CreatedBookResponse(String title, List<Author> authors, int availableCopies, Category category, Publisher publisher) {
        this.title = title;
        this.authors = authors;
        this.availableCopies = availableCopies;
        this.category = category;
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

    public int getAvailableCopies() {
        return availableCopies;
    }
    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Category getCategory() {
        return category;
    }
    public void setCategory(Category category) {
        this.category = category;
    }

    public Publisher getPublisher() {
        return publisher;
    }
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}
