package com.websitesaoviet.WebsiteSaoViet.dto.request.admin;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.BookingCheckoutDetailResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EmailInvoiceRequest {
    String to;
    String subject;
    boolean confirm;
    String id;
}