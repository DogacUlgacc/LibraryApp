package com.grup_7.LibraryApp.dto.memberDto.request;

import com.grup_7.LibraryApp.enums.member.MembershipLevel;

public class UpdateMembershipLevelRequest {
    private MembershipLevel membershipLevel;

    public MembershipLevel getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(MembershipLevel membershipLevel) {
        this.membershipLevel = membershipLevel;
    }
}
