package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.EmailRequest;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.MailService;
import jakarta.mail.MessagingException;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class MailController {
     MailService mailService;

     @PreAuthorize("hasRole('ADMIN')")
     @PostMapping("/send")
     public String sendEmail(@RequestParam String to, @RequestParam String subject, @RequestParam String htmlContent) {
         try {
             mailService.sendMail(to, subject, htmlContent);

             return "Email đã gửi thành công";
         } catch (MessagingException e) {
             throw new AppException(ErrorCode.EMAIL_SEND_FAILED);
         }
     }

     @PreAuthorize("hasRole('ADMIN')")
     @PostMapping("/queue")
     public String queueEmail(@RequestBody List<EmailRequest> emailRequests) {
         for (EmailRequest email : emailRequests) {
             mailService.sendToQueue(email.getTo(), email.getSubject(), email.getText());
         }

         return "Emails added to queue!";
     }
}