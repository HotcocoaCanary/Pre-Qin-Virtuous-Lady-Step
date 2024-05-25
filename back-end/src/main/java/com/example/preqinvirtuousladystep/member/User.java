package com.example.preqinvirtuousladystep.member;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "UserID")
    private Integer userId;

    @Column(name = "Name", nullable = false, length = 50)
    @NotNull
    private String name;

    @Column(name = "Password", nullable = false, length = 32)
    private String password;

    @Column(name = "Age")
    private Integer age;

    @Column(name = "Gender", nullable = false, length = 3)
    @NotNull
    private String gender;

    @Column(name = "Email", nullable = false, unique = true, length = 100)
    @NotEmpty
    @Email
    private String email;

    @Column(name = "PhoneNumber", nullable = false, unique = true, length = 20)
    @NotEmpty
    private String phoneNumber;

    @Column(name = "RegistrationDate", nullable = false)
    private LocalDate registrationDate;
}
