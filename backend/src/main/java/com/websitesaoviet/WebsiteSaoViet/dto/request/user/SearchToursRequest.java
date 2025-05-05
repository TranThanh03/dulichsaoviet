package com.websitesaoviet.WebsiteSaoViet.dto.request.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SearchToursRequest {
    String keyword;
    String sort;
}