package com.websitesaoviet.WebsiteSaoViet.dto.response.common;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NewsResponse {
    String id;
    String title;
    String summary;
    String image;
    String content;
    String type;
    int viewCount;
    LocalDateTime timeStamp;
}