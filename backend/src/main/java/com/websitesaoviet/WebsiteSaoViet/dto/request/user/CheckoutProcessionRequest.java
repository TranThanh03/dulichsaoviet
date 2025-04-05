package com.websitesaoviet.WebsiteSaoViet.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckoutProcessionRequest {
    String scheduleId;
    String promotionId;
    int quantityAdult;
    int quantityChildren;
    String method;
}