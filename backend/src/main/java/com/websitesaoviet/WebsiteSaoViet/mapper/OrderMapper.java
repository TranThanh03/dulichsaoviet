package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.request.OrderCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    Order createOrder(OrderCreationRequest request);

    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toOrderListResponse(List<Order> orderList);
}
