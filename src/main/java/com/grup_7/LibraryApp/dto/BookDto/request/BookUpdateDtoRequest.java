package com.grup_7.LibraryApp.dto.BookDto.request;

public class BookUpdateDtoRequest {


    private String title;
    private Integer authorId;
    private Integer publishYear;
    private Integer categoryId;
    private Integer totalCopies;
    private Integer publisherId;

    public BookUpdateDtoRequest() {
    }

    public BookUpdateDtoRequest(String title,
                                Integer authorId,
                                Integer publishYear,
                                Integer categoryId,
                                Integer totalCopies,
                                Integer publisherId) {
        this.title = title;
        this.publishYear = publishYear;
        this.totalCopies = totalCopies;
        this.authorId = authorId;
        this.categoryId = categoryId;
        this.publisherId = publisherId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public Integer getPublishYear() {
        return publishYear;
    }

    public void setPublishYear(Integer publishYear) {
        this.publishYear = publishYear;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Integer getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Integer publisherId) {
        this.publisherId = publisherId;
    }

    public Integer getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(Integer totalCopies) {
        this.totalCopies = totalCopies;
    }
}


