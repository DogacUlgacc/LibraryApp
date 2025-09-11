package com.grup_7.LibraryApp.dto.publisherDto.request;

public class CreatePublisherRequestDto {

    private String name;
    private String address;

    public CreatePublisherRequestDto() {};
    public CreatePublisherRequestDto(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
