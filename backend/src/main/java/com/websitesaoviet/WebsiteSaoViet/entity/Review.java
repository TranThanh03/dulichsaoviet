package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "review")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    Long id;

    @Column(name = "tour_id")
    String tourId;

    @Column(name = "customer_id")
    String customerId;

    @Column(name = "rating")
    int rating;

    @Column(name = "comment")
    String comment;

    @Column(name = "time_stamp")
    LocalDateTime timeStamp;
}