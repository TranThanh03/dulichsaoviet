package com.websitesaoviet.WebsiteSaoViet.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MailService {
    JavaMailSender mailSender;
    RabbitTemplate rabbitTemplate;

    String QUEUE_NAME = "mailQueue";

    @NonFinal
    @Value("${spring.mail.fromName}")
    protected String yourEmail;

    public void sendMail(String to, String subject, String htmlContent) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(htmlContent, true);
        helper.setFrom(yourEmail);

        mailSender.send(message);
    }

    public void sendToQueue(String to, String subject, String htmlContent) {
        String emailData = to + ";" + subject + ";" + htmlContent;
        rabbitTemplate.convertAndSend(QUEUE_NAME, emailData);
    }

    @RabbitListener(queues = "mailQueue")
    public void consumeEmailQueue(String message) throws MessagingException {
        String[] parts = message.split(";");

        if (parts.length == 3) {
            sendMail(parts[0], parts[1], parts[2]);
        }
    }
}