package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LatestOrdersResponse {
    String orderCode;
    String customerCode;
    String tourName;
    LocalDateTime orderDatetime;
    int totalPrice;
}