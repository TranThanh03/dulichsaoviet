package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CheckoutProcessionRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.UrlCheckoutResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CheckoutController {
    CheckoutService checkoutService;
    ScheduleService scheduleService;
    AuthenticationService authenticationService;
    PromotionService promotionService;

    @PostMapping("/process")
    ResponseEntity<ApiResponse<UrlCheckoutResponse>> processCheckout(
            @RequestHeader("Authorization") String authorizationHeader,
            @RequestBody CheckoutProcessionRequest request) {

        var schedule = scheduleService.getScheduleById(request.getScheduleId());

        int quantityPeople = request.getQuantityAdult() + request.getQuantityChildren();
        if (!scheduleService.existsScheduleByQuantityPeople(request.getScheduleId(), quantityPeople)) {
            throw new AppException(ErrorCode.SCHEDULE_PEOPLE_INVALID);
        }

        Double adultPrice = schedule.getAdultPrice();
        Double childrenPrice = schedule.getChildrenPrice();
        Double discount = 0.0;

        if (!request.getPromotionId().trim().equals("")) {
            try {
                var promotion = promotionService.getAvailablePromotionById(request.getPromotionId());
                discount = promotion.getDiscount();
            } catch (Exception e) {
                throw new AppException(ErrorCode.PROMOTION_NOT_EXITED);
            }
        }

        Double amount = adultPrice * request.getQuantityAdult() + childrenPrice * request.getQuantityChildren();

        if (request.getMethod().equals("momo") || request.getMethod().equals("vnpay")) {
            amount = amount - discount;
        }

        String checkoutUrl = "";
        Random random = new Random();
        String orderId = System.currentTimeMillis() + "" + random.nextInt(1000);
        int responseCode;
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        String customerId = authenticationService.getIdByToken(token);

        switch (request.getMethod()) {
            case "momo":
                responseCode = 1901;
                checkoutUrl = checkoutService.processMomoCheckout(orderId, customerId, amount, request);
                break;
            case "vnpay":
                responseCode = 1902;
                checkoutUrl = checkoutService.processVnpayCheckout(orderId, customerId, amount, request);
                break;
            case "cash":
                responseCode = 1903;
                checkoutUrl =  checkoutService.resultCashCheckout(orderId, customerId, amount, request);
                break;
            default:
                responseCode = 1900;
                break;
        }

        ApiResponse<UrlCheckoutResponse> apiResponse = ApiResponse.<UrlCheckoutResponse>builder()
                .code(responseCode)
                .result(
                        UrlCheckoutResponse.builder()
                                .checkoutUrl(checkoutUrl)
                                .build()
                )
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/momo/callback")
    public ResponseEntity<String> handleMomoCallback(@RequestBody Map<String, Object> data) {
        try {
            JSONObject jsonData = new JSONObject(data);

            String bookingCode = jsonData.optString("orderId", "");
            String checkoutCode = jsonData.optString("transId", "");
            int resultCode = jsonData.optInt("resultCode", -1);

            if (bookingCode.isEmpty() || checkoutCode.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            if (resultCode == 0) {
                String extraData = jsonData.optString("extraData", "");
                String[] extraParams = extraData.split(";");

                String scheduleId = (extraParams.length > 0 && extraParams[0].contains("=")) ? extraParams[0].split("=", 2)[1] : "";
                String customerId = (extraParams.length > 1 && extraParams[1].contains("=")) ? extraParams[1].split("=", 2)[1] : "";
                int quantityAdult = (extraParams.length > 2 && extraParams[2].contains("=")) ? Integer.parseInt(extraParams[2].split("=", 2)[1]) : 0;
                int quantityChildren = (extraParams.length > 3 && extraParams[3].contains("=")) ? Integer.parseInt(extraParams[3].split("=", 2)[1]) : 0;
                String promotionId = (extraParams.length > 4 && extraParams[4].contains("=")) ? extraParams[4].split("=", 2)[1] : "";

                Double amount = jsonData.optDouble("amount", 0);

                checkoutService.resultMomoCheckout(bookingCode, checkoutCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, promotionId);

                return ResponseEntity.ok("OK");
            }

            return ResponseEntity.badRequest().body("ERROR");
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback processing error");
        }
    }

    @GetMapping("/vnpay/callback")
    public ResponseEntity<String> handleVnpayCallback(@RequestParam Map<String, String> params) {
        try {
            String bookingCode = params.get("vnp_TxnRef");
            String checkoutCode = params.get("vnp_TransactionNo");
            String vnp_ResponseCode = params.get("vnp_ResponseCode");
            String vnp_OrderInfo = params.get("vnp_OrderInfo");
            String vnp_Amount = params.get("vnp_Amount");

            if (bookingCode.isEmpty() || checkoutCode.isEmpty()) {
                return ResponseEntity.badRequest().body("Missing required fields");
            }

            if ("00".equals(vnp_ResponseCode)) {
                String[] orderInfoParts = vnp_OrderInfo.split(";");

                Map<String, String> extraData = new HashMap<>();
                for (int i = 1; i < orderInfoParts.length; i++) {
                    String[] kv = orderInfoParts[i].split("=");
                    if (kv.length == 2) {
                        extraData.put(kv[0], kv[1]);
                    }
                }

                String scheduleId = extraData.getOrDefault("scheduleId", "");
                String customerId = extraData.getOrDefault("customerId", "");
                int quantityAdult = Integer.parseInt(extraData.getOrDefault("quantityAdult", "0"));
                int quantityChildren = Integer.parseInt(extraData.getOrDefault("quantityChildren", "0"));
                String promotionId = extraData.getOrDefault("promotionId", "");

                Double amount = Double.parseDouble(vnp_Amount) / 100;

                checkoutService.resultVnpayCheckout(bookingCode, checkoutCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, promotionId);

                return ResponseEntity.ok("OK");
            }

            return ResponseEntity.badRequest().body("ERROR");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback error");
        }
    }


//    @GetMapping("/status/{id}")
//    public ResponseEntity<ApiResponse<CheckoutStatusResponse>> getStatusByCheckoutId(@PathVariable String id){
//        ApiResponse<CheckoutStatusResponse> apiResponse = ApiResponse.<CheckoutStatusResponse>builder()
//                .code(1900)
//                .result(checkoutService.getStatusByCheckoutId(id))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//

    //    @PutMapping("/{id}")
//    ResponseEntity<ApiResponse<CheckoutResponse>> updateCheckout(@PathVariable String id, @RequestBody CheckoutUpdateRequest request) {
//        ApiResponse<CheckoutResponse> apiResponse = ApiResponse.<CheckoutResponse>builder()
//                .code(1900)
//                .message("Cập nhật thông tin hóa đơn thành công.")
//                .result(checkoutService.updateCheckout(id, request))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }

}