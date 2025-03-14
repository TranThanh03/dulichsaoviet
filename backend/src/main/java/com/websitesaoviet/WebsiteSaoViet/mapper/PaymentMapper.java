package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.response.PaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Payment;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    PaymentResponse toPaymentResponse(Payment payment);

    List<PaymentResponse> toListPaymentsResponse(List<Payment> paymentsList);
}
