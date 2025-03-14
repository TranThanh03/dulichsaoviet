package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "guide")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Guide {
    @Id
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "avatar")
    String avatar;

    @Column(name = "sex")
    String sex;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Column(name = "phone")
    String phone;

    @Column(name = "email")
    String email;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    String description;

    @Column(name = "evaluate")
    int evaluate;
}
