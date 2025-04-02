package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "schedule")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "code", unique = true)
    String code;

    @Column(name = "tour_id")
    String tourId;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "quantity_people")
    int quantityPeople;

    @Column(name = "total_people")
    int totalPeople;

    @Column(name = "adult_price")
    Double adultPrice;

    @Column(name = "children_price")
    Double childrenPrice;

    @Column(name = "status")
    String status;
}