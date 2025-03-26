package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.PaymentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.PaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment createPayment(PaymentCreationRequest request);

    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toPaymentListResponse(List<Payment> paymentList);
}
