package com.websitesaoviet.WebsiteSaoViet.dto.request.admin;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsCreationRequest {
    String title;
    String summary;
    String image;
    String content;
    String type;
}