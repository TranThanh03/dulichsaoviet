package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

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
    String orderCode;
    String customerCode;
    String userName;
    String phone;
    String email;
    String tourCode;
    String tourName;
    String tourImage;
    String guideCode;
    String guideName;
    String guideAvatar;
    LocalDate startDate;
    LocalDate endDate;
    int tourPrice;
    int guidePrice;
    int numberOfPeople;
    int totalPrice;
    LocalDateTime orderDatetime;
    String status;
    String paymentCode;
    int amount;
    String method;
    LocalDateTime paymentDatetime;
    String paymentStatus;
}