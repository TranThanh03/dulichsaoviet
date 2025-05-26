package com.websitesaoviet.WebsiteSaoViet.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ChatMessagesResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ChatToursResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.ChatSessions;
import com.websitesaoviet.WebsiteSaoViet.entity.Messages;
import com.websitesaoviet.WebsiteSaoViet.enums.CommonStatus;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.repository.ChatSessionsRepository;
import com.websitesaoviet.WebsiteSaoViet.repository.MessagesRepository;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ChatbotService {
    @NonFinal
    @Value("${gemini.api-key}")
    private String apiKey;

    @NonFinal
    @Value("${gemini.url}")
    private String GEMINI_URL;

    @NonFinal
    private final RestTemplate restTemplate = new RestTemplate();

    ChatSessionsRepository chatSessionsRepository;
    MessagesRepository messagesRepository;
    SequenceService sequenceService;

    public String sendPrompt(String message) {
        try {
            String url = String.format(GEMINI_URL, apiKey);

            String systemInstruction = "Bạn là trợ lý hỗ trợ thông tin về tour du lịch" +
                    "Chỉ trả lời câu hỏi nếu nó liên quan đến tour ở Việt Nam." +
                    "Không lặp lại câu hỏi của người dùng trong câu trả lời." +
                    "Nếu trong câu hỏi có liên quan đến điểm đến hoặc tour, ví dụ: Hà Nội, Hạ Long,... thì gán destination: Hà Nội, Hạ Long,..." +
                    "Nếu trong câu hỏi mà chỉ có 1 điểm đến hoặc 1 tour, ví dụ: Hà Nội thì trả về JSON chứa destination: Hà Nội." +
                    "Nếu trong câu hỏi có liên quan đến 2 hay nhiều điểm đến hoặc tour, ví dụ: 'Hà Nội, Hạ Long', 'Hà Nội - Hạ Long',... thì gán destination: [Hà Nội, Hạ Long,...]" +
                    "Nếu trong câu hỏi có liên quan đến thời gian, số ngày ví dụ: 2 ngày, 2 ngày 1 đêm,... thì gán quantityDay: 2,..." +
                    "Nếu trong câu hỏi có liên quan đến khu vực(Miền Bắc, Miền Trung, Miền Nam) thì gán area lần lượt là: b, t, n." +
                    "Nếu trong câu hỏi có liên quan đến ngày khởi hành, ngày xuất phát, ví dụ: 23/05/2025, 23-05-2025,... thì gán vào startDate: 2025-05-23,..." +
                    "Nếu trong câu hỏi có liên quan đến ngày kết thúc, ví dụ: 23/05/2025, 23-05-2025,... thì gán vào endDate: 2025-05-23,..." +
                    "Nếu trong câu hỏi có liên quan đến giá, ví dụ: 5 triệu, 5.000.000, 5000000, thì gán vào maxPrice: 5000000" +
                    "Nếu trong câu hỏi có liên quan đến hai giá ví dụ: 4 đến 5 triệu, 4.000.000 - 5.000.000, 4000000 - 5000000, thì gán vào minPrice: 4000000 và maxPrice: 5000000." +
                    "Nếu trong câu hỏi có liên quan đến mới nhất, cũ nhất, 'giá cao đến thấp, thấp nhất', 'giá thấp đến cao, cao nhất' thì gán sorted lần lượt là: new, old, high-to-low, low-to-high." +
                    "Nếu trong câu hỏi có liên quan đến danh sách điểm đến, tour hot, phổ biến,... thì bạn tìm kiếm thông tin trên mạng và gán danh sách chỉ chứa tên(loại bỏ các thông tin không cần thiết) vào destination: [tên danh sách điểm điến]" +
                    "Nếu truy xuất được thông tin liên quan đến các thuộc tính: destination, quantityDay, area, startDate, endDate, minPrice, maxPrice, sorted thì trả về JSON chứa các thuộc tính đó." +
                    "Nếu có bất kỳ lỗi định dạng hoặc thông tin không hợp lệ nào (ví dụ sai định dạng ngày,...) thì CHỈ trả về văn bản mô tả lỗi, KHÔNG được bao gồm JSON." +
                    "Nếu ngày khởi hành hoặc ngày kết thúc mà KHÔNG ĐÚNG định dạng dd/MM/yyyy(25/05/2025) thì trả về văn bản mô tả lỗi là: 'Vui lòng nhập ngày đúng định dạng. Ví dụ: 25/05/2025.'." +
                    "Nếu câu hỏi không liên quan đến destination, quantityDay, area, startDate, endDate, minPrice, maxPrice, sorted thì tìm kiếm thông tin trên mạng và trả lời." +
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

            return text;
        } catch (Exception e) {
            throw new AppException(ErrorCode.CHATBOT_ERROR);
        }
    }

    public String generateCode() {
        return getNextCode("chatbot");
    }

    public List<ChatMessagesResponse> getChatByCustomerCode(String code) {
        if (!chatSessionsRepository.existsChatSessionsByCustomerCode(code)) {
            ChatSessions chatSessions = new ChatSessions();

            chatSessions.setCustomerCode(code);
            chatSessions.setStartedAt(LocalDateTime.now());
            chatSessions.setStatus(CommonStatus.IN_PROGRESS.getValue());

            chatSessionsRepository.save(chatSessions);
        }

        List<ChatMessagesResponse> messages = chatSessionsRepository.findByCustomerCode(code);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        for (ChatMessagesResponse msg : messages) {
            Object contentObj = msg.getContent();

            if (contentObj instanceof String) {
                String content = ((String) contentObj).trim();

                if (content.startsWith("[") && content.endsWith("]")) {
                    try {
                        List<Map<String, Object>> parsedContent = mapper.readValue(
                                content, new TypeReference<List<Map<String, Object>>>() {});
                        msg.setContent(parsedContent);
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            }
        }

        return messages;
    }

    public void updateChat(String id, String inputMessage) {
        chatSessionsRepository.update(id, LocalDateTime.now());
        createMessage(id, "customer", inputMessage.trim().replaceAll("\"", ""), null);
    }

    public void createMessage(String chatId, String senderType, String message, List<ChatToursResponse> result) {
        String content = "";
        Messages messages = new Messages();

        try {
            if (result != null) {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

                content = mapper.writeValueAsString(result);
            } else {
                content = message.trim();
            }

            messages.setChatId(chatId);
            messages.setSenderType(senderType);
            messages.setContent(content);
            messages.setCreatedAt(LocalDateTime.now());

            messagesRepository.save(messages);
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

    public String getNextCode(String type) {
        int nextCode = sequenceService.getNextNumber(type.toLowerCase());

        return "CB" + Year.now().getValue() + String.format("%06d", nextCode);
    }
}