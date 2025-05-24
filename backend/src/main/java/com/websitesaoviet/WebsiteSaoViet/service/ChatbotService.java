package com.websitesaoviet.WebsiteSaoViet.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ChatbotService {
    @NonFinal
    @Value("${gemini.api-key}")
    private String apiKey;

    @NonFinal
    @Value("${gemini.url}")
    private String GEMINI_URL;

    @NonFinal
    private final RestTemplate restTemplate = new RestTemplate();

    public Object sendPrompt(String message) {
        String url = String.format(GEMINI_URL, apiKey);

        String systemInstruction = "Bạn là trợ lý hỗ trợ thông tin về tour du lịch." +
                "Chỉ trả lời câu hỏi nếu nó liên quan đến tour." +
                "Không lặp lại câu hỏi của người dùng trong câu trả lời." +
                "Nếu trong câu hỏi có liên quan đến điểm đến hoặc tour, ví dụ: Hà Nội, Hạ Long,... thì gán destination: Hà Nội, Hạ Long,..." +
                "Nếu trong câu hỏi có liên quan đến thời gian, số ngày ví dụ: 2 ngày, 2 ngày 1 đêm,... thì gán quantityDay: 2,..." +
                "Nếu trong câu hỏi có liên quan đến khu vực(Miền Bắc, Miền Trung, Miền Nam) thì gán area lần lượt là: b, t, n." +
                "Nếu trong câu hỏi có liên quan đến ngày khởi hành, ngày xuất phát, ví dụ: 23/05/2025, 23-05-2025, 23-05, 05-2025,... thì gán vào startDate: 2025-05-23,..." +
                "Nếu trong câu hỏi có liên quan đến ngày kết thúc, ví dụ: 23/05/2025, 23-05-2025, 23-05, 05-2025,... thì gán vào endDate: 2025-05-23,..." +
                "Nếu trong câu hỏi có liên quan đến giá, ví dụ: 5 triệu, 5.000.000, 5000000, thì gán vào adultPrice: 5000000" +
                "Nếu trong câu hỏi có liên quan đến mới nhất, cũ nhất, 'giá cao đến thấp, thấp nhất', 'giá thấp đến cao, cao nhất' thì gán sorted lần lượt là: new, old, high-to-low, low-to-high." +
                "Nếu trong câu hỏi có liên quan đến danh sách điểm đến, tour hot, phổ biến,... thì bạn tìm kiếm thông tin trên mạng và gán danh sách chỉ chứa tên(loại bỏ các thông tin không cần thiết) vào destination: [tên danh sách điểm điến]" +
                "Nếu câu trả lời liên quan đến destination, quantityDay, area, startDate, endDate, adultPrice, sorted thì trả về dạng JSON." +
                "Nếu câu hỏi không liên quan đến destination, quantityDay, area, startDate, endDate, adultPrice, sorted thì tìm kiếm thông tin trên mạng và trả lời." +
                "Nếu câu hỏi không liên quan đến tour, trả lời: 'Xin lỗi, tôi chỉ hỗ trợ về tour du lịch.'";

        String finalPrompt = systemInstruction + "\n\nCâu hỏi: " + message;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(Map.of("parts", List.of(Map.of("text", finalPrompt))))
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Map.class
        );

        List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.getBody().get("candidates");
        Map<String, Object> first = candidates.get(0);
        Map<String, Object> content = (Map<String, Object>) first.get("content");
        List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
        String text = parts.get(0).get("text").toString().replaceAll("\n", "").trim();

        if (text.startsWith("```json")) {
            text = text.replaceAll("```json", "").replaceAll("```", "").trim();
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(text, Map.class);
        } catch (Exception e) {
            return text;
        }
    }
}