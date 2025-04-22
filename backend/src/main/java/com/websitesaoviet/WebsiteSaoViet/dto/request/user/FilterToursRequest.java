package com.websitesaoviet.WebsiteSaoViet.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterToursRequest {
    List<Double> price;
    String destination;
    Integer rating;
    Integer duration;
    String sort;
}