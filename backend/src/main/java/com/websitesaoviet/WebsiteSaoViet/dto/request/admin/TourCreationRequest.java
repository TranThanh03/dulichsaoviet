package com.websitesaoviet.WebsiteSaoViet.dto.request.admin;

import com.websitesaoviet.WebsiteSaoViet.entity.Itinerary;
import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourCreationRequest {
    String name;
    String destination;
    String area;
    List<String> image;
    List<Itinerary> itinerary;
    String description;

    @Min(value = 1, message = "DAY_INVALID")
    int quantityDay;
}