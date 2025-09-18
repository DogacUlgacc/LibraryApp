package com.grup_7.LibraryApp.rules;

import com.grup_7.LibraryApp.core.exception.type.BusinessException;
import com.grup_7.LibraryApp.entity.Member;
import com.grup_7.LibraryApp.enums.member.MembershipLevel;
import com.grup_7.LibraryApp.repository.MemberRepository;
import org.springframework.stereotype.Component;


@Component
public class MemberBusinessRules {

    private final MemberRepository memberRepository;

    public MemberBusinessRules(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void emailMustNotExistWithSameName(String email) {
        Member memberWithSameName = memberRepository.findTop1ByEmailIgnoreCase(email).orElse(null);
        if (memberWithSameName != null) {
            throw new BusinessException("Bu mail ile daha önce kayıt oluşturulmuştur.");
        }
    }
    public void phoneNumberMustNotExistWithSamePhoneNumber(String phone) {
        Member memberWithSamePhoneNumber = memberRepository.findTop1ByPhoneIgnoreCase(phone).orElse(null);
        if (memberWithSamePhoneNumber != null) {
            throw new BusinessException("Bu telefon numarası ile daha önce kayıt oluşturulmuştur.");
        }
    }
    public void checkLoanLimit(Member member) {

        if (member.getMembershipLevel() == MembershipLevel.BANNED) {
            throw new BusinessException("Bu üye banlandı.");
        }

        long activeLoans = member.getLoans().stream()
                .filter(loan -> loan.getLoanDate() == null)
                .count();

        if (member.getMembershipLevel() == MembershipLevel.STANDARD && activeLoans >= 3) {
            throw new BusinessException("STANDARD üyeler en fazla 3 ödünç alabilir.");
        }

        if (member.getMembershipLevel() == MembershipLevel.GOLD && activeLoans >= 5) {
            throw new BusinessException("GOLD üyeler en fazla 5 ödünç alabilir.");
        }
    }
    public void validatePhoneNumber(String phoneNumber) {
        if (!phoneNumber.matches("^(\\+90|0)?5\\d{9}$")) {
            throw new BusinessException("Geçerli bir telefon numarası giriniz");
        }
    }
}
