package com.grup_7.LibraryApp.dto.publisherDto.response;

public class CreatedPublisherResponse {
    private String name;
    private String address;

    public CreatedPublisherResponse() {
    }
    public CreatedPublisherResponse(String name, String address) {
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
