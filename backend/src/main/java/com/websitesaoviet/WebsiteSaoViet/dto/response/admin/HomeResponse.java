package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeResponse {
    long quantityTour;
    long quantityGuide;
    long quantityUser;
    long quantityOrder;
    long quantityTotalPrice;
}