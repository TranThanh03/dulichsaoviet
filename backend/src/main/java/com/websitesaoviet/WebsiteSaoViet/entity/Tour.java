package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "tour")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Tour {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "tour_code", unique = true)
    String tourCode;

    @Column(name = "name")
    String name;

    @Column(name = "image")
    String image;

    @Column(name = "introduce", columnDefinition = "LONGTEXT")
    String introduce;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    String description;

    @Column(name = "category_id")
    int categoryId;

    @Column(name = "price")
    int price;

    @Column(name = "time_created")
    LocalDateTime timeCreated;

    @Column(name = "orders")
    int orders;
}