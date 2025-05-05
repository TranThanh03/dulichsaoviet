package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchToursResponse {
    String id;
    String name;
    String destination;
    String image;
    int quantityDay;
    Double adultPrice;
    int people;
    int rating;
    LocalDateTime createdTime;
}