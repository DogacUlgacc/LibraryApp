package com.grup_7.LibraryApp.entity;

import com.grup_7.LibraryApp.enums.book.BookStatus;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private int id;

    @Column(name = "title", nullable = false, length = 255)
    private String title;

    @Column(name = "publish_year")
    private int publishYear;

    @ManyToMany
    @JoinTable(name = "book_authors",               // ara tablo adı
            joinColumns = @JoinColumn(name = "book_id"),   // kitap id
            inverseJoinColumns = @JoinColumn(name = "author_id") // yazar id
    )
    private List<Author> authors = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "total_copies")
    private int totalCopies;

    @Column(name = "available_copies")
    private int availableCopies;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "publisher_id", nullable = false)
    private Publisher publisher;

    @Column(name = "isbn", nullable = true, unique = true, length = 20)
    private String isbn;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = true)
    private BookStatus status = BookStatus.ACTIVE;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(int publishYear) {
        this.publishYear = publishYear;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
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

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }

    public Book(int id, String title, int publishYear, List<Author> authors, Category category, int totalCopies, int availableCopies, Publisher publisher) {
        this.id = id;
        this.title = title;
        this.publishYear = publishYear;
        this.authors = authors;
        this.category = category;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
        this.publisher = publisher;
    }
}