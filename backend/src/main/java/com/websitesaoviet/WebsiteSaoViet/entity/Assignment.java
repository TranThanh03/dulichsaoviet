package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Entity
@Table(name = "assignment")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "assignment_code", unique = true)
    String assignmentCode;

    @Column(name = "tour_id")
    String tourId;

    @Column(name = "guide_id")
    String guideId;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @Column(name = "number_of_people")
    int numberOfPeople;

    @Column(name = "total_people")
    int totalPeople;

    @Column(name = "guide_price")
    int guidePrice;

    @Column(name = "status")
    String status;
}