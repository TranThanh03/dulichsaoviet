package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.PaymentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.PaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Checkout;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Checkout createPayment(PaymentCreationRequest request);

    PaymentResponse toPaymentResponse(Checkout checkout);

    List<PaymentResponse> toPaymentListResponse(List<Checkout> checkoutList);
}
