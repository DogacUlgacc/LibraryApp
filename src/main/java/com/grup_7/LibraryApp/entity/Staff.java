package com.grup_7.LibraryApp.entity;




import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "staff")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "staff_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 120)
    private String name;

    @Column(name = "surname", nullable = false, length = 120)
    private String surname;

    @Column(name = "role", length = 80)
    private String role;

    @Column(name = "email", length = 200)
    private String email;

    @Column(name = "phone", length = 40)
    private String phone;
}