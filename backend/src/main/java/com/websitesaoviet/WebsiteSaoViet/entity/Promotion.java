package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "promotion")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "code")
    String code;

    @Column(name = "title")
    String title;

    @Column(name = "description")
    String description;

    @Column(name = "discount")
    Double discount;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "status")
    String status;

    @Column(name = "created_time")
    LocalDateTime createdTime;
}