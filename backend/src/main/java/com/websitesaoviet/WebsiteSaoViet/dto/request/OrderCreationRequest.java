package com.websitesaoviet.WebsiteSaoViet.dto.request;

import jakarta.validation.constraints.Min;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderCreationRequest {
    String userId;
    String userName;
    String tourId;
    String tourName;
    String guideId;
    String guideName;
    String assignmentId;
    LocalDate startDate;
    LocalDate endDate;
    int tourPrice;
    int guidePrice;

    @Min(value = 1, message = "PEOPLE_INVALID")
    int numberOfPeople;

    double totalPrice;
}