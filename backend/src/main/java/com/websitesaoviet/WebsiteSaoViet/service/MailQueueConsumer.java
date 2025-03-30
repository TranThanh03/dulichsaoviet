package com.websitesaoviet.WebsiteSaoViet.service;

import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailQueueConsumer {
    MailService mailService;

    @RabbitListener(queues = "mailQueue")
    public void consumeEmailQueue(String message)
            throws MessagingException {
        String[] parts = message.split(";");

        if (parts.length == 3) {
            mailService.sendMail(parts[0], parts[1], parts[2]);
        }
    }
}