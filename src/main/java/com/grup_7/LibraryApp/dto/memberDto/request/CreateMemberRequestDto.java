package com.grup_7.LibraryApp.dto.memberDto.request;

import com.grup_7.LibraryApp.enums.member.MembershipLevel;

public class CreateMemberRequestDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private MembershipLevel membershipLevel;

    public CreateMemberRequestDto() {
    }

    public CreateMemberRequestDto(MembershipLevel membershipLevel, String address, String phone, String email, String surname, String name) {
        this.membershipLevel = membershipLevel;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.surname = surname;
        this.name = name;
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

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
}
