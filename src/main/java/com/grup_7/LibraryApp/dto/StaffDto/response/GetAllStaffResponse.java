package com.grup_7.LibraryApp.dto.StaffDto.response;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllStaffResponse {
    private Long id;
    private String name;
    private String surname;
    private String role;
}
//tüm stafı listelerken