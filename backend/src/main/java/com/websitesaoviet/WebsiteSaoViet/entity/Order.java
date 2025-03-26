package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "order_code", unique = true)
    String orderCode;

    @Column(name = "customer_id")
    String customerId;

    @Column(name = "customer_code")
    String customerCode;

    @Column(name = "tour_id")
    String tourId;

    @Column(name = "tour_code")
    String tourCode;

    @Column(name = "tour_name")
    String tourName;

    @Column(name = "guide_id")
    String guideId;

    @Column(name = "guide_code")
    String guideCode;

    @Column(name = "assignment_id")
    String assignmentId;

    @Column(name = "assignment_code")
    String assignmentCode;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "tour_price")
    int tourPrice;

    @Column(name = "guide_price")
    int guidePrice;

    @Column(name = "number_of_people")
    int numberOfPeople;

    @Column(name = "total_price")
    int totalPrice;

    @Column(name = "order_datetime")
    LocalDateTime orderDatetime;

    @Column(name = "status")
    String status;
}