package com.websitesaoviet.WebsiteSaoViet.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderYearRequest {
    @Min(value = 2025, message = "DATE_INVALID")
    int year;
}