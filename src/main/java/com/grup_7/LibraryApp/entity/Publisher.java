package com.grup_7.LibraryApp.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "publishers")
@Getter
@Setter
public class Publisher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    // Constructor
    public Publisher(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public Publisher() {
    }

}