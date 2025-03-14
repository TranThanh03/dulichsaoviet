package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponse {
    String id;
    String orderId;
    String customerId;
    String tourId;
    String guideId;
    String assignmentId;
    int totalPrice;
    String paymentStatus;
    LocalDateTime orderDatetime;
    String status;
}