package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutResponse {
    String id;
    String code;
    String bookingId;
    String method;
    LocalDateTime checkoutTime;
    String status;
}