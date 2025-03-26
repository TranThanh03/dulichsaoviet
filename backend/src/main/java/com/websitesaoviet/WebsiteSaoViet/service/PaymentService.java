package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.PaymentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.PaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Payment;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.PaymentMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.PaymentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PaymentService {
    PaymentRepository paymentRepository;
    PaymentMapper paymentMapper;
    MomoPaymentService momoPaymentService;
    OrderService orderService;
    AssignmentService assignmentService;

    @NonFinal
    @Value("${momo.url}")
    protected String momoUrl;

    public PaymentResponse createPayment(
            String orderId, String paymentId, int amount, String method, LocalDateTime dateTime, String status) {
        Payment payment = new Payment();

        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentDatetime(dateTime);
        payment.setStatus(status);

        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getPayments() {
        return paymentMapper.toPaymentListResponse(paymentRepository.findAll());
    }

    public PaymentResponse getPaymentById(String id) {
        return  paymentMapper.toPaymentResponse(paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_EXITED)));
    }

    public String processMomoPayment(String orderId, PaymentCreationRequest request) {
        String ipnUrl = momoUrl + "/api/payment/momo/callback";
        String redirectUrl = "http://localhost:3000/orders/message";
        int amount = request.getAmount();
        String extraData = "assignmentId=" + request.getAssignmentId() + ";customerId=" + request.getCustomerId() + ";people=" + request.getNumberOfPeople();

        return momoPaymentService.createPayment(amount, orderId, ipnUrl, redirectUrl, extraData);
    }

    public void resultMoMoPayment(
            String orderId, String transId, String customerId, String assignmentId,
            int numberOfPeople, int amount) {
        String method = "MoMo";
        LocalDateTime dateTime = LocalDateTime.now();
        String status = "Đã thanh toán";

        var newOrder = orderService.createOrder(orderId, customerId, assignmentId, numberOfPeople, amount);
        createPayment(newOrder.getId(), transId, amount, method, dateTime, status);

        assignmentService.addNumberOfPeople(assignmentId, numberOfPeople);
    }

    public String resultLaterPayment(String orderId, PaymentCreationRequest request) {
        String customerId = request.getCustomerId();
        String assignmentId = request.getAssignmentId();
        int numberOfPeople = request.getNumberOfPeople();
        int amount = request.getAmount();

        orderService.createOrder(orderId, customerId, assignmentId, numberOfPeople, amount);

        LocalDateTime nextDayMidnight = LocalDate.now().plusDays(1).atStartOfDay();
        String formattedTime = nextDayMidnight.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

        return String.format("Vui lòng thanh toán trước %s!", formattedTime);
    }

    public String getStatusByPaymentCode(String code) {
        boolean isSuccess = paymentRepository.existsPaymentByPaymentCode(code);

        String paymentStatus = isSuccess ? "success" : "failed";

        return paymentStatus;
    }

    public String processMomoPaymentLater(String orderId, String userOrderId, int amount) {
        String ipnUrl = momoUrl + "/api/payment/momo/callback/later";
        String redirectUrl = "http://localhost:3000/orders/message";
        String extraData = "later=true;userOrderId=" + userOrderId;

        return momoPaymentService.createPayment(amount, orderId, ipnUrl, redirectUrl, extraData);
    }

    public void resultMoMoPaymentLater(String orderId, String transId, int amount) {
        String method = "MoMo";

        var payment = orderService.getPaymentByOrderId(orderId);
//        createPayment(payment.getPaymentId(), transId, method, amount);

        assignmentService.addNumberOfPeople(payment.getAssignmentId(), payment.getNumberOfPeople());
    }
}