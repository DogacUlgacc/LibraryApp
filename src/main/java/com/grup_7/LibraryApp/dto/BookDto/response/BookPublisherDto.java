package com.grup_7.LibraryApp.dto.BookDto.response;

public class BookPublisherDto {
    private String name;
    private String address;

    public BookPublisherDto() { }

    public BookPublisherDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
