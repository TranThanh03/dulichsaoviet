package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SimilarToursResponse {
    String id;
    String name;
    String destination;
    List<String> image;
    int quantityDay;
}