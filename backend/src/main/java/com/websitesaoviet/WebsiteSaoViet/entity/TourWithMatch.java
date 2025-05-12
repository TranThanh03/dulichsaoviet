package com.websitesaoviet.WebsiteSaoViet.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PUBLIC)
public class TourWithMatch {
    Tour tour;
    int matchCount;
}