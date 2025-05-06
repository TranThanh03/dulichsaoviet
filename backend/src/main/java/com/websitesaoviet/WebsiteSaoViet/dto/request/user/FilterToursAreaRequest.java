package com.websitesaoviet.WebsiteSaoViet.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FilterToursAreaRequest {
    List<Double> price;
    String area;
    LocalDate startDate;
    LocalDate endDate;
    Integer duration;
    String sort;
}