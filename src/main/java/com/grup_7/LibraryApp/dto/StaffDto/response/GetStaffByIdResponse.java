package com.grup_7.LibraryApp.dto.StaffDto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetStaffByIdResponse {
    private Long id;
    private String name;
    private String surname;
    private String role;
    private String email;
    private String phone;
}
//tek bir tane staffın detayı dönülürken