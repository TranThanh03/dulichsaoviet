package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.OrderCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Booking createOrder(OrderCreationRequest request);

    OrderResponse toOrderResponse(Booking booking);

    List<OrderResponse> toOrderListResponse(List<Booking> bookingList);
}
