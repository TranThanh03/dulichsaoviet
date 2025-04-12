package com.websitesaoviet.WebsiteSaoViet.dto.request.user;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
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

    @Min(value = 1, message = "QUANTITY_ADULT")
    @Max(value = 100, message = "QUANTITY_ADULT")
    int quantityAdult;

    @Min(value = 1, message = "QUANTITY_CHILDREN")
    @Max(value = 100, message = "QUANTITY_CHILDREN")
    int quantityChildren;
    String method;
}