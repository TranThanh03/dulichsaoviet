package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CheckoutResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Checkout;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CheckoutMapper {
    CheckoutResponse toCheckoutResponse(Checkout checkout);
}
