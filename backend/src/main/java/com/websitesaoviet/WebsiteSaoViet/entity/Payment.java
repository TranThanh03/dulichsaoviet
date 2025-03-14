package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "payment")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "payment_id")
    String paymentId;

    @Column(name = "order_id")
    String orderId;

    @Column(name = "amount")
    int amount;

    @Column(name = "method")
    String method;

    @Column(name = "payment_datetime")
    LocalDateTime paymentDatetime;

    @Column(name = "status")
    String status;
}