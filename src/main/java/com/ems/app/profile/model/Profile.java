package com.ems.app.profile.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "user_id", unique = true)
    private Long userId;
    private String firstName;
    private String lastName;
    @Email
    private String email;
    private String phoneNumber;
    private String country;
    @Column(name = "ranks")
    private String rank;
    private String shipType;
    private Date onboardDate;
    private String profilePhotoUrl;

}
