package com.websitesaoviet.WebsiteSaoViet.dto.response.user;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatMessagesResponse {
    String id;
    String content;
    String senderType;
    LocalDateTime createdTime;
}