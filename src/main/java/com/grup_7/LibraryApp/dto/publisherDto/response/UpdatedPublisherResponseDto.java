package com.grup_7.LibraryApp.dto.publisherDto.response;

public class UpdatedPublisherResponseDto {

    private String name;
    private String address;


    public UpdatedPublisherResponseDto(String name, String address) {
        this.name = name;
        this.address = address;
    }
    public UpdatedPublisherResponseDto(){};

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
