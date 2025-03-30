package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "customer")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "code", unique = true)
    String code;

    @Column(name = "full_name")
    String fullName;

    @Column(name = "phone", unique = true)
    String phone;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "password")
    String password;

    @ElementCollection
    @CollectionTable(name = "customer_roles", joinColumns = @JoinColumn(name = "id"))
    @Column(name = "role")
    Set<String> roles = new HashSet<>();

    @Column(name = "registered_time")
    LocalDateTime registeredTime;

    @Column(name = "status")
    String status;
}