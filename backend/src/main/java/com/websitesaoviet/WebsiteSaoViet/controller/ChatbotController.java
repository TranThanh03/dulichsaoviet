package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.service.ChatbotService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chatbot")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ChatbotController {
    ChatbotService chatbotService;

    @PostMapping("/{id}")
    ResponseEntity<ApiResponse<Object>> ask(@RequestBody String message) {
        Object result = chatbotService.sendPrompt(message);

        ApiResponse<Object> apiResponse = ApiResponse.<Object>builder()
                .code(2200)
                .result(result)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

}