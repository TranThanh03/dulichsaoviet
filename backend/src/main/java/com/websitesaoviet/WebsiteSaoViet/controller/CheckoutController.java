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
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/checkouts")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CheckoutController {
    CheckoutService checkoutService;
    ScheduleService scheduleService;
    BookingService bookingService;
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
//            case "vnpay":
//                responseCode = 1902;
//                checkoutUrl = checkoutService.processVnpayCheckout(orderId, customerId, request);
//                break;
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
                String scheduleId = extraParams.length > 0 ? extraParams[0].split("=")[1] : "";
                String customerId = extraParams.length > 1 ? extraParams[1].split("=")[1] : "";
                int quantityAdult = extraParams.length > 2 ? Integer.parseInt(extraParams[2].split("=")[1]) : 0;
                int quantityChildren = extraParams.length > 3 ? Integer.parseInt(extraParams[3].split("=")[1]) : 0;
                String promotionId = extraParams.length > 4 ? extraParams[4].split("=")[1] : "";

                Double amount = jsonData.optDouble("amount", 0);

                checkoutService.resultMoMoCheckout(bookingCode, checkoutCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, promotionId);

                return ResponseEntity.ok("OK");
            }

            return ResponseEntity.badRequest().body("ERROR");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback processing error");
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
//    @PostMapping("/process/later/{userBookingId}")
//    ResponseEntity<ApiResponse<UrlCheckoutResponse>> processCheckoutLater(@PathVariable String userBookingId, @RequestBody UserCheckoutLaterRequest request) {
//        String checkoutUrl = "";
//        var booking = bookingService.getBookingById(userBookingId);
//
//        if (scheduleService.existsSchedule(booking.getScheduleId(), booking.getNumberOfPeople())) {
//            throw new AppException(ErrorCode.ASSIGNMENT_PEOPLE_INVALID);
//        }
//
//        Random random = new Random();
//        String bookingId = System.currentTimeMillis() + "" + random.nextInt(1000);
//        int amount = booking.getTotalPrice();
//
//        switch (request.getMethod()) {
//            case "momo":
//                checkoutUrl = checkoutService.processMomoCheckoutLater(orderId, userBookingId, amount);
//                break;
//            case "vnpay":
//
//                break;
//            default:
//                return null;
//        }
//
//        ApiResponse<UrlCheckoutResponse> apiResponse = ApiResponse.<UrlCheckoutResponse>builder()
//                .code(1900)
//                .result(
//                        UrlCheckoutResponse.builder()
//                                .checkoutUrl(checkoutUrl)
//                                .build()
//                )
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PostMapping("/momo/callback/later")
//    public ResponseEntity<String> handleMomoCallbackLater(@RequestBody Map<String, Object> data) {
//        try {
//            JSONObject jsonData = new JSONObject(data);
//
//            String extraData = jsonData.optString("extraData", "");
//            String[] extraParams = extraData.split(";");
//            String userBookingId = extraParams.length > 1 ? extraParams[1].split("=")[1] : "";
//
//            String transId = jsonData.optString("transId", "");
//            int amount = jsonData.optInt("amount", 0);
//            int resultCode = jsonData.optInt("resultCode", -1);
//
//            if (userBookingId.isEmpty() || transId.isEmpty()) {
//                return ResponseEntity.badRequest().body("Missing required fields");
//            }
//
//            if (resultCode == 0) {
//                checkoutService.resultMoMoCheckoutLater(userBookingId, transId, amount);
//                return ResponseEntity.ok("OK");
//            }
//
//            return ResponseEntity.badRequest().body("ERROR");
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Callback processing error");
//        }
//    }

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