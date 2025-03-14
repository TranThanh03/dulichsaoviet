package com.websitesaoviet.WebsiteSaoViet.mapper;

import com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Order;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderResponse toOrderResponse(Order order);

    List<OrderResponse> toListOrdersResponse(List<Order> ordersList);
}
