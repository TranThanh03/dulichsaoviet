package com.websitesaoviet.WebsiteSaoViet.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailQueueProducer {
    RabbitTemplate rabbitTemplate;
    String QUEUE_NAME = "mailQueue";

    public void sendToQueue(String to, String subject, String htmlContent) {
        String emailData = to + ";" + subject + ";" + htmlContent;

        rabbitTemplate.convertAndSend(QUEUE_NAME, emailData);
    }
}