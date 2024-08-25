package com.pahul.captureanything.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "\"user\"")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "country", nullable = false, length = 100)
    private String country;

    @OneToMany
    @JoinColumn(name = "id")
    private List<Preferences> preference;
}
