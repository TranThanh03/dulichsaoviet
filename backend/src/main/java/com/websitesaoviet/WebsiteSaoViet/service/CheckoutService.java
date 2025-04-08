package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CheckoutProcessionRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CheckoutResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Checkout;
import com.websitesaoviet.WebsiteSaoViet.enums.CheckoutStatus;
import com.websitesaoviet.WebsiteSaoViet.enums.MethodPayment;
import com.websitesaoviet.WebsiteSaoViet.mapper.CheckoutMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.CheckoutRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CheckoutService {
    CheckoutMapper checkoutMapper;
    CheckoutRepository checkoutRepository;
    ScheduleService scheduleService;
    MomoService momoService;
    VnpayService vnpayService;
    PromotionService promotionService;
    BookingService bookingService;

    @NonFinal
    @Value("${base.url}")
    protected String baseUrl;

    public CheckoutResponse createCheckout(String bookingId, String checkoutCode, String method,
                                           LocalDateTime currentTime, String status) {

        Checkout checkout = new Checkout();

        checkout.setCode(checkoutCode);
        checkout.setBookingId(bookingId);
        checkout.setMethod(method);
        checkout.setCheckoutTime(currentTime);
        checkout.setStatus(status);

        return checkoutMapper.toCheckoutResponse(checkoutRepository.save(checkout));
    }
//
//    public List<CheckoutResponse> getCheckouts() {
//        return checkoutMapper.toListCheckoutsResponse(checkoutRepository.findAll());
//    }
//
//    public CheckoutResponse getCheckoutById(String id) {
//        return  checkoutMapper.toCheckoutResponse(checkoutRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_EXITED)));
//    }
//
//    public CheckoutResponse updateCheckout(String id, String checkoutId, String method, int amount) {
//        Checkout checkout = checkoutRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_EXITED));
//
//        if (checkout != null) {
//            checkout.setCheckoutId(checkoutId);
//            checkout.setMethod(method);
//            checkout.setAmount(amount);
//            checkout.setCheckoutDatetime(LocalDateTime.now());
//            checkout.setStatus("Đã thanh toán");
//        }
//
//        return checkoutMapper.toCheckoutResponse(checkoutRepository.save(checkout));
//    }

    public String processMomoCheckout(String orderId, String customerId, Double amount, CheckoutProcessionRequest request) {
        String ipnUrl = baseUrl + "/api/v1/checkouts/momo/callback";
        String redirectUrl = "http://localhost:3000/booking/message";
        String promotionId = request.getPromotionId().trim();

        String extraData = "scheduleId=" + request.getScheduleId() + ";customerId=" + customerId + ";quantityAdult=" + request.getQuantityAdult() + ";quantityChildren=" + request.getQuantityChildren() + ";promotionId=" + promotionId;
        int amountInt = amount.intValue();

        return momoService.createPayment(amountInt, orderId, ipnUrl, redirectUrl, extraData);
    }

    public String processVnpayCheckout(String orderId, String customerId, Double amount, CheckoutProcessionRequest request) {
        String ipnUrl = baseUrl + "/api/v1/checkouts/vnpay/callback";
        String redirectUrl = "http://localhost:3000/booking/message";
        String promotionId = request.getPromotionId().trim();

        String extraData = "scheduleId=" + request.getScheduleId() + ";customerId=" + customerId + ";quantityAdult=" + request.getQuantityAdult() + ";quantityChildren=" + request.getQuantityChildren() + ";promotionId=" + promotionId;
        int amountInt = amount.intValue();

        return vnpayService.createPayment(amountInt, orderId, ipnUrl, redirectUrl, extraData);
    }

    public void resultMomoCheckout(String bookingCode, String checkoutCode, String customerId,
                                   String scheduleId, int quantityAdult, int quantityChildren,
                                   Double amount, String promotionId) {

        String method = MethodPayment.MOMO.getValue();
        LocalDateTime currentTime = LocalDateTime.now();
        String status = CheckoutStatus.PAID.getValue();
        int people = quantityAdult + quantityChildren;
        Double discount = 0.0;

        if (!promotionId.trim().equals("")) {
            var promotion = promotionService.getPromotionById(promotionId);

            discount = promotion.getDiscount();
            promotionService.minusQuantity(promotionId, 1);
        }

        var newBooking = bookingService.createBooking(bookingCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, discount);

        createCheckout(newBooking.getId(), checkoutCode, method, currentTime, status);

        scheduleService.addQuantityPeople(scheduleId, people);
    }

    public void resultVnpayCheckout(String bookingCode, String checkoutCode, String customerId,
                                   String scheduleId, int quantityAdult, int quantityChildren,
                                   Double amount, String promotionId) {

        String method = MethodPayment.VNPAY.getValue();
        LocalDateTime currentTime = LocalDateTime.now();
        String status = CheckoutStatus.PAID.getValue();
        int people = quantityAdult + quantityChildren;
        Double discount = 0.0;

        if (!promotionId.trim().equals("")) {
            var promotion = promotionService.getPromotionById(promotionId);

            discount = promotion.getDiscount();
            promotionService.minusQuantity(promotionId, 1);
        }

        var newBooking = bookingService.createBooking(bookingCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, discount);

        createCheckout(newBooking.getId(), checkoutCode, method, currentTime, status);

        scheduleService.addQuantityPeople(scheduleId, people);
    }

    public String resultCashCheckout(String bookingCode, String customerId, Double amount, CheckoutProcessionRequest request) {
        LocalDate currentDate = LocalDate.now();
        String scheduleId = request.getScheduleId();
        var schedule = scheduleService.getScheduleById(scheduleId);
        LocalDate startDate = schedule.getStartDate();

        if (currentDate.isBefore(startDate.minusDays(2))) {

            int quantityAdult = request.getQuantityAdult();
            int quantityChildren = request.getQuantityChildren();
            String method = MethodPayment.CASH.getValue();
            String status = CheckoutStatus.UNPAID.getValue();
            Double discount = 0.0;

            var newBooking = bookingService.createBooking(bookingCode, customerId, scheduleId, quantityAdult, quantityChildren, amount, discount);

            createCheckout(newBooking.getId(), "", method, null, status);

            LocalDateTime oneDayBeforeStart = startDate.minusDays(1).atStartOfDay();
            String formattedTime = oneDayBeforeStart.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

            return String.format("Vui lòng đến quầy thanh toán trước %s!", formattedTime);
        } else {
            return "Vui lòng thay đổi phương thức thanh toán khác!";
        }
    }

//    public CheckoutStatusResponse getStatusByCheckoutId(String id) {
//        boolean isSuccess = checkoutRepository.existsCheckoutById(id);
//
//        String checkoutStatus = isSuccess ? "success" : "failed";
//
//        return new CheckoutStatusResponse(checkoutStatus);
//    }
}