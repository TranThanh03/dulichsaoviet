package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterToursAreaResponse {
    String id;
    String name;
    String destination;
    String image;
    int quantityDay;
    Double adultPrice;
    LocalDate startDate;
    LocalDate endDate;
    int people;
    int rating;
}