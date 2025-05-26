package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "chat_sessions")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ChatSessions {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", unique = true)
    String id;

    @Column(name = "customer_code")
    String customerCode;

    @Column(name = "started_at")
    LocalDateTime startedAt;

    @Column(name = "updated_at")
    LocalDateTime updatedAt;

    @Column(name = "status")
    String status;
}