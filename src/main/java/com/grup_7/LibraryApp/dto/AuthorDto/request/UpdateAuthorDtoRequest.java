package com.grup_7.LibraryApp.dto.AuthorDto.request;

public class UpdateAuthorDtoRequest {
    private String phoneNumber;
    private String email;

    public UpdateAuthorDtoRequest() {
    }

    public UpdateAuthorDtoRequest(String phoneNumber, String email) {
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
}
