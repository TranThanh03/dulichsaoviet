package com.websitesaoviet.WebsiteSaoViet.dto.response.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BookingStatisticResponse {
    int month;
    Long canceled;
    Long confirmed;
    Double revenue;
}