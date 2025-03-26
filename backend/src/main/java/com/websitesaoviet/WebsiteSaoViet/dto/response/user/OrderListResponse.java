package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderListResponse {
    String id;
    String orderCode;
    String tourId;
    String tourName;
    String tourImage;
    String guideId;
    String guideName;
    LocalDate startDate;
    LocalDate endDate;
    int numberOfPeople;
    double totalPrice;
    LocalDateTime orderDatetime;
    String orderStatus;
    String method;
    String paymentStatus;
}