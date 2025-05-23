package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import com.websitesaoviet.WebsiteSaoViet.entity.Itinerary;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourResponse {
    String id;
    String code;
    String name;
    String destination;
    String area;
    List<String> image;
    List<Itinerary> itinerary;
    String description;
    int quantityDay;
    LocalDateTime timeStamp;
    int quantityOrder;
}