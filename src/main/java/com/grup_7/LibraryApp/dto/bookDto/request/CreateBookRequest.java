package com.grup_7.LibraryApp.dto.bookDto.request;

public class CreateBookRequest {
    private String title;
    private Integer authorId;
    private Integer publishYear;
    private Integer categoryId;
    private Integer totalCopies;
    private Integer publisherId;

    public CreateBookRequest() { }

    public CreateBookRequest(String title,
                             Integer authorId,
                             Integer publishYear,
                             Integer categoryId,
                             Integer totalCopies,
                             Integer publisherId) {
        this.title = title;
        this.authorId = authorId;
        this.publishYear = publishYear;
        this.categoryId = categoryId;
        this.totalCopies = totalCopies;
        this.publisherId = publisherId;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Integer getAuthorId() { return authorId; }
    public void setAuthorId(Integer authorId) { this.authorId = authorId; }

    public Integer getPublishYear() { return publishYear; }
    public void setPublishYear(Integer publishYear) { this.publishYear = publishYear; }

    public Integer getCategoryId() { return categoryId; }
    public void setCategoryId(Integer categoryId) { this.categoryId = categoryId; }

    public Integer getTotalCopies() { return totalCopies; }
    public void setTotalCopies(Integer totalCopies) { this.totalCopies = totalCopies; }

    public Integer getPublisherId() { return publisherId; }
    public void setPublisherId(Integer publisherId) { this.publisherId = publisherId; }
}
