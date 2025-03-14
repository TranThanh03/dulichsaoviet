package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.request.UserPaymentRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentStatusResponse;
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

        payment.setPaymentId(paymentId);
        payment.setOrderId(orderId);
        payment.setAmount(amount);
        payment.setMethod(method);
        payment.setPaymentDatetime(dateTime);
        payment.setStatus(status);

        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getPayments() {
        return paymentMapper.toListPaymentsResponse(paymentRepository.findAll());
    }

    public PaymentResponse getPaymentById(String id) {
        return  paymentMapper.toPaymentResponse(paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_EXITED)));
    }

    public PaymentResponse updatePayment(String id, String paymentId, String method, int amount) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_EXITED));

        if (payment != null) {
            payment.setPaymentId(paymentId);
            payment.setMethod(method);
            payment.setAmount(amount);
            payment.setPaymentDatetime(LocalDateTime.now());
            payment.setStatus("Đã thanh toán");
        }

        return paymentMapper.toPaymentResponse(paymentRepository.save(payment));
    }

    public String processMomoPayment(String orderId, UserPaymentRequest request) {
        String ipnUrl = momoUrl + "/api/payment/momo/callback";
        String redirectUrl = "http://localhost:3000/orders/message";
        int amount = request.getAmount();
        String extraData = "assignmentId=" + request.getAssignmentId() + ";userId=" + request.getUserId() + ";people=" + request.getNumberOfPeople();

        return momoPaymentService.createPayment(amount, orderId, ipnUrl, redirectUrl, extraData);
    }

    public void resultMoMoPayment(
            String orderId, String transId, String userId, String assignmentId,
            int numberOfPeople, int amount) {
        String method = "MoMo";
        LocalDateTime dateTime = LocalDateTime.now();
        String status = "Đã thanh toán";

        var newOrder = orderService.createOrder(orderId, userId, assignmentId, numberOfPeople, amount);
        createPayment(newOrder.getId(), transId, amount, method, dateTime, status);

        assignmentService.addNumberOfPeople(assignmentId, numberOfPeople);
    }

    public String resultLaterPayment(String orderId, UserPaymentRequest request) {
        String userId = request.getUserId();
        String assignmentId = request.getAssignmentId();
        String method = "Thanh toán sau";
        int numberOfPeople = request.getNumberOfPeople();
        int amount = request.getAmount();
        String status = "Chưa thanh toán";

        var newOrder = orderService.createOrder(orderId, userId, assignmentId, numberOfPeople, amount);
        createPayment(newOrder.getId(), "", 0, method, null, status);

        LocalDateTime nextDayMidnight = LocalDate.now().plusDays(1).atStartOfDay();
        String formattedTime = nextDayMidnight.format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy"));

        return String.format("Vui lòng thanh toán trước %s!", formattedTime);
    }

    public PaymentStatusResponse getStatusByPaymentId(String id) {
        boolean isSuccess = paymentRepository.existsPaymentById(id);

        String paymentStatus = isSuccess ? "success" : "failed";

        return new PaymentStatusResponse(paymentStatus);
    }

    public String processMomoPaymentLater(String orderId, String userOrderId, int amount) {
        String ipnUrl = momoUrl + "/api/payment/momo/callback/later";
        String redirectUrl = "http://localhost:3000/orders/message";
        String extraData = "later=true;userOrderId=" + userOrderId;

        return momoPaymentService.createPayment(amount, orderId, ipnUrl, redirectUrl, extraData);
    }

    public void resultMoMoPaymentLater(String userOrderId, String transId, int amount) {
        String method = "MoMo";

        var payment = orderService.getPaymentByOrderId(userOrderId);
        updatePayment(payment.getPaymentId(), transId, method, amount);

        assignmentService.addNumberOfPeople(payment.getAssignmentId(), payment.getNumberOfPeople());
    }

    public void deleteByUserId(String userId) {
        paymentRepository.deleteByUserId(userId);
    }
}