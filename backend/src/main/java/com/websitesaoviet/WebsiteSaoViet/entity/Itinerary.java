package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Embeddable

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Itinerary {
    @Column(name = "title")
    String title;

    @Column(name = "day_number")
    int dayNumber;

    @Column(name = "description", columnDefinition = "LONGTEXT")
    String description;
}