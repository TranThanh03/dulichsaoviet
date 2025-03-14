package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderDetailResponse {
    String id;
    String orderId;
    String customerId;
    String userName;
    String phone;
    String email;
    String tourId;
    String tourName;
    String tourImage;
    String guideId;
    String guideName;
    String guideAvatar;
    String assignmentId;
    LocalDate startDate;
    LocalDate endDate;
    int tourPrice;
    int guidePrice;
    int numberOfPeople;
    int totalPrice;
    LocalDateTime orderDatetime;
    String status;
    String paymentId;
    int amount;
    String method;
    LocalDateTime paymentDatetime;
    String paymentStatus;
}