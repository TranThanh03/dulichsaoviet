package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.AdminUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.EmailRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.common.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AdminResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.service.AdminService;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import com.websitesaoviet.WebsiteSaoViet.service.MailQueueProducer;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/mail")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class MailController {
    MailQueueProducer mailQueueProducer;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/send")
    public String sendBulkMail(@RequestBody List<EmailRequest> emailRequests) {
        for (EmailRequest email : emailRequests) {
            mailQueueProducer.sendToQueue(email.getTo(), email.getSubject(), email.getText());
        }

        return "Emails added to queue!";
    }
}