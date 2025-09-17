package com.grup_7.LibraryApp.dto.memberDto.response;

import com.grup_7.LibraryApp.enums.member.MembershipLevel;

public class UpdatedMemberResponseDto {
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private MembershipLevel membershipLevel;
    public UpdatedMemberResponseDto() {}

    public UpdatedMemberResponseDto(String name, String surname, String email, String phone, String address, MembershipLevel membershipLevel) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.membershipLevel = membershipLevel;
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
