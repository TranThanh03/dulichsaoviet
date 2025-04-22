package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterToursResponse {
    String id;
    String name;
    String destination;
    String image;
    int quantityDay;
    Double adultPrice;
    int people;
    int rating;
}