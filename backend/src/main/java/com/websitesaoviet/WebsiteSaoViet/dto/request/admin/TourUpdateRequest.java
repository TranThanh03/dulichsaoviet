package com.websitesaoviet.WebsiteSaoViet.dto.request.admin;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.JoinColumn;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TourUpdateRequest {
    String name;
    String destination;
    String area;
    List<String> image;
    List<String> itinerary;
    String description;

    @Min(value = 1, message = "DAY_INVALID")
    int quantityDay;
}