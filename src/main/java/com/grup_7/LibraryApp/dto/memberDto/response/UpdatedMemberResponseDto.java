package com.grup_7.LibraryApp.dto.memberDto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UpdatedMemberResponseDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String status;
    public UpdatedMemberResponseDto() {}
    public UpdatedMemberResponseDto(String name, String surname, String email, String phone, String address, String status) {
        this.name = name;
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
