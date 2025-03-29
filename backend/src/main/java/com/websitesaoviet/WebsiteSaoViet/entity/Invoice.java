package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "invoice")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    Long id;

    @Column(name = "booking_id")
    String bookingId;

    @Column(name = "amount")
    Double amount;

    @Column(name = "details")
    String details;

    @Column(name = "time_issued")
    LocalDateTime timeIssued;
}