package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.UserPaymentLaterRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.UserPaymentRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentStatusResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.UrlPaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AssignmentService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
import com.websitesaoviet.WebsiteSaoViet.service.PaymentService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api/payment")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentController {
    PaymentService paymentService;
    AssignmentService assignmentService;
    OrderService orderService;

    @GetMapping()
    ResponseEntity<ApiResponse<List<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse>>> getPayments() {
        ApiResponse<List<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse>> apiResponse = ApiResponse.<List<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse>>builder()
                .code(1948)
                .result(paymentService.getPayments())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse>> getPaymentById(@PathVariable String id) {
        ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse> apiResponse = ApiResponse.<com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse>builder()
                .code(1947)
                .result(paymentService.getPaymentById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

//    @PutMapping("/{id}")
//    ResponseEntity<ApiResponse<PaymentResponse>> updatePayment(@PathVariable String id, @RequestBody PaymentUpdateRequest request) {
//        ApiResponse<PaymentResponse> apiResponse = ApiResponse.<PaymentResponse>builder()
//                .code(1946)
//                .message("Cập nhật thông tin hóa đơn thành công.")
//                .result(paymentService.updatePayment(id, request))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }

    @PostMapping("/process")
    ResponseEntity<ApiResponse<UrlPaymentResponse>> processPayment(@RequestBody UserPaymentRequest request) {
        String paymentUrl = "";
        Random random = new Random();
        String orderId = System.currentTimeMillis() + "" + random.nextInt(1000);
        int responseCode = 1945;

        if (assignmentService.existsAssignment(request.getAssignmentId(), request.getNumberOfPeople())) {
            throw new AppException(ErrorCode.ASSIGNMENT_PEOPLE_INVALID);
        }

        switch (request.getMethod()) {
            case "momo":
                paymentUrl = paymentService.processMomoPayment(orderId, request);
                break;
            case "vnpay":

                break;
            case "later":
                responseCode = 1944;
                paymentUrl =  paymentService.resultLaterPayment(orderId, request);
                break;
            default:
                return null;
        }

        ApiResponse<UrlPaymentResponse> apiResponse = ApiResponse.<UrlPaymentResponse>builder()
                .code(responseCode)
                .result(
                        UrlPaymentResponse.builder()
                                .paymentUrl(paymentUrl)
                                .build()
                )
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/callback")
    public ResponseEntity<String> handleMomoCallback(@RequestBody Map<String, Object> data) {
        try {
            JSONObject jsonData = new JSONObject(data);

            String extraData = jsonData.optString("extraData", "");
            String[] extraParams = extraData.split(";");
            String assignmentId = extraParams.length > 0 ? extraParams[0].split("=")[1] : "";
            String userId = extraParams.length > 1 ? extraParams[1].split("=")[1] : "";
            int people = extraParams.length > 2 ? Integer.parseInt(extraParams[2].split("=")[1]) : 0;

            String orderId = jsonData.optString("orderId", "");
            String transId = jsonData.optString("transId", "");
            int amount = jsonData.optInt("amount", 0);
            int resultCode = jsonData.optInt("resultCode", -1);

            if (orderId.isEmpty() || transId.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            if (resultCode == 0) {
                paymentService.resultMoMoPayment(orderId, transId, userId, assignmentId, people, amount);
                return ResponseEntity.ok("OK");
            }

            return ResponseEntity.badRequest().body("ERROR");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback processing error");
        }
    }

    @GetMapping("/status/{id}")
    public ResponseEntity<ApiResponse<PaymentStatusResponse>> getStatusByPaymentId(@PathVariable String id){
        ApiResponse<PaymentStatusResponse> apiResponse = ApiResponse.<PaymentStatusResponse>builder()
                .code(1943)
                .result(paymentService.getStatusByPaymentId(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/process/later/{userOrderId}")
    ResponseEntity<ApiResponse<UrlPaymentResponse>> processPaymentLater(@PathVariable String userOrderId, @RequestBody UserPaymentLaterRequest request) {
        String paymentUrl = "";
        var order = orderService.getOrderById(userOrderId);

        if (assignmentService.existsAssignment(order.getAssignmentId(), order.getNumberOfPeople())) {
            throw new AppException(ErrorCode.ASSIGNMENT_PEOPLE_INVALID);
        }

        Random random = new Random();
        String orderId = System.currentTimeMillis() + "" + random.nextInt(1000);
        int amount = order.getTotalPrice();

        switch (request.getMethod()) {
            case "momo":
                paymentUrl = paymentService.processMomoPaymentLater(orderId, userOrderId, amount);
                break;
            case "vnpay":

                break;
            default:
                return null;
        }

        ApiResponse<UrlPaymentResponse> apiResponse = ApiResponse.<UrlPaymentResponse>builder()
                .code(1942)
                .result(
                        UrlPaymentResponse.builder()
                                .paymentUrl(paymentUrl)
                                .build()
                )
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/callback/later")
    public ResponseEntity<String> handleMomoCallbackLater(@RequestBody Map<String, Object> data) {
        try {
            JSONObject jsonData = new JSONObject(data);

            String extraData = jsonData.optString("extraData", "");
            String[] extraParams = extraData.split(";");
            String userOrderId = extraParams.length > 1 ? extraParams[1].split("=")[1] : "";

            String transId = jsonData.optString("transId", "");
            int amount = jsonData.optInt("amount", 0);
            int resultCode = jsonData.optInt("resultCode", -1);

            if (userOrderId.isEmpty() || transId.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            if (resultCode == 0) {
                paymentService.resultMoMoPaymentLater(userOrderId, transId, amount);
                return ResponseEntity.ok("OK");
            }

            return ResponseEntity.badRequest().body("ERROR");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback processing error");
        }
    }
}