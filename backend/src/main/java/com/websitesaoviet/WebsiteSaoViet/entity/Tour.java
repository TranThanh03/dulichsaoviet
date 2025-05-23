package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

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

    @Column(name = "code", unique = true)
    String code;

    @Column(name = "name", length = 500)
    String name;

    @Column(name = "destination", length = 500)
    String destination;

    @Column(name = "area")
    String area;

    @ElementCollection
    @CollectionTable(name = "tour_images", joinColumns = @JoinColumn(name = "tour_id"))
    @Column(name = "image")
    List<String> image;

    @ElementCollection
    @CollectionTable(name = "tour_itineraries", joinColumns = @JoinColumn(name = "tour_id"))
    List<Itinerary > itinerary;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    String description;

    @Column(name = "quantity_day")
    int quantityDay;

    @Column(name = "time_stamp")
    LocalDateTime timeStamp;

    @Column(name = "quantity_order")
    int quantityOrder;
}