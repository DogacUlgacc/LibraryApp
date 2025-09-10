package com.grup_7.LibraryApp.dto.StaffDto.request;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class StaffForUpdateDto {
    private String name;
    private String surname;
    private String role;
    private String email;
    private String phone;
}
//staffı güncellerken