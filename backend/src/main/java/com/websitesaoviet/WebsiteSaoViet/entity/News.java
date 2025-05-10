package com.websitesaoviet.WebsiteSaoViet.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Entity
@Table(name = "news")

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    @Column(name = "code")
    String code;

    @Column(name = "title")
    String title;

    @Column(name = "summary")
    String summary;

    @Column(name = "image")
    String image;

    @Column(name = "content")
    String content;

    @Column(name = "type")
    String type;

    @Column(name = "view_count")
    int viewCount;

    @Column(name = "time_stamp")
    LocalDateTime timeStamp;
}