package com.websitesaoviet.WebsiteSaoViet.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PaymentResponse {
    String id;
    String paymentId;
    String orderId;
    double amount;
    String method;
    LocalDateTime paymentDatetime;
}