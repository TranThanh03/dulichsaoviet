package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "messages")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Messages {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "chat_id")
    String chatId;

    @Column(name = "sender_type")
    String senderType;

    @Column(name = "content", columnDefinition = "LONGTEXT")
    String content;

    @Column(name = "created_at")
    LocalDateTime createdAt;
}