package com.grup_7.LibraryApp.dto.publisherDto.request;

public class CreatePublisherDtoRequest {

    private String name;
    private String address;

    public CreatePublisherDtoRequest() {};
    public CreatePublisherDtoRequest(String name, String address) {
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
