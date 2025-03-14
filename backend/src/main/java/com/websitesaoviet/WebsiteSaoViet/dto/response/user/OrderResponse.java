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
public class OrderResponse {
    String id;
    String orderId;
    String tourId;
    String tourName;
    String tourImage;
    String guideId;
    String guideName;
    LocalDate startDate;
    LocalDate endDate;
    int tourPrice;
    int guidePrice;
    int numberOfPeople;
    double totalPrice;
    LocalDateTime orderDatetime;
    String orderStatus;
    String payId;
    String paymentId;
    String method;
    LocalDateTime paymentDatetime;
    String paymentStatus;
}