package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.*;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderDetailResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderPaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderListResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.OrderMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.OrderRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderService {
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    CustomerService customerService;
    AssignmentService assignmentService;
    TourService tourService;

    public com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderResponse createOrder (
            String orderId, String userId, String assignmentId,
            int numberOfPeople, int amount) {
        Booking booking = new Booking();
        var user = customerService.getCustomerById(userId);
        var assignment = assignmentService.getAssignmentById(assignmentId);

//        order.setOrderCode(orderId);
//        order.setCustomerId(userId);
//        order.setCustomerCode(user.getUserId());
//        order.setTourId(assignment.getTourId());
//        order.setTourName(assignment.getTourName());
//        order.setGuideId(assignment.getGuideId());
//        order.setAssignmentId(assignmentId);
//        order.setStartDate(assignment.getStartDate());
//        order.setEndDate(assignment.getEndDate());
//        order.setTourPrice(assignment.getTourPrice());
//        order.setGuidePrice(assignment.getGuidePrice());
//        order.setNumberOfPeople(numberOfPeople);
//        order.setTotalPrice(amount);
//        order.setOrderDatetime(LocalDateTime.now());
//        order.setStatus("Đang xử lý");

        return orderMapper.toOrderResponse(orderRepository.save(booking));
    }

    public List<com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderResponse> getOrders() {
        return orderMapper.toOrderListResponse(orderRepository.findAll());
    }

    public Page<OrderListResponse> getAllOrders(Pageable pageable) {
        return orderRepository.getAllOrders(pageable);
    }

    public List<OrderListResponse> getOrdersByCustomerId(String customerId) {
        return orderRepository.findOrderListByCustomerId(customerId);
    }

    public OrderResponse getOrderById(String id) {
        return orderMapper.toOrderResponse(orderRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXITED)));
    }

    public OrderDetailResponse getOrderDetail(String id) {
        return orderRepository.getOrderDetail(id);
    }

    public OrderPaymentResponse getPaymentByOrderId(String id) {
        return orderRepository.getPaymentByOrderId(id);
    }

    @Transactional
    public void cancelOrder(String id) {
        var order = orderRepository.existsOrderProcessing(id);

        if (order == null) {
            throw new AppException(ErrorCode.INVALID_ORDER);
        }

        order.setStatus("Đã hủy");
        orderRepository.save(order);

        var assignment = orderRepository.getAssignmentByOrderId(id);

        if(assignment != null) {
            assignmentService.minusNumberOfPeople(assignment.getAssignmentId(), assignment.getNumberOfPeople());
        }
        else {
            orderRepository.deleteById(id);
        }
    }

    public void confirmOrder(String id) {
        var order = orderRepository.existsOrdersPaid(id);

        if (order == null) {
            throw new AppException(ErrorCode.INVALID_ORDER);
        }

        var tour = orderRepository.getTourByOrderId(id);
        order.setStatus("Đã xác nhận");

        if(tour != null) {
            tourService.addOders(tour.getAssignmentId(), tour.getNumberOfPeople());
        }

        orderRepository.save(order);
    }

    public long countOrders() {
        return orderRepository.countOrders();
    }

    public long getTotalRevenue() {
        return orderRepository.getTotalRevenue();
    }

    public List<LatestOrdersResponse> getLatestOrders() {
        return orderRepository.getLatestOrders();
    }

    public boolean existsByCustomerId(String customerId) {
        return orderRepository.existsByCustomerId(customerId);
    }

    public boolean existsByAssignmentId(String assignmentId) {
        return orderRepository.existsByAssignmentId(assignmentId);
    }

    public boolean existsByTourId(String tourId) {
        return orderRepository.existsByTourId(tourId);
    }

    public boolean existsByGuideId(String guideId) {
        return orderRepository.existsByGuideId(guideId);
    }

    public OrderStatusResponse getOrderStatusCounts() {
        return orderRepository.getOrderStatusCounts();
    }

    public List<OrderStatisticsResponse> getOrdersStatisticsByMonth() {
        int year = LocalDate.now().getYear();

        return orderRepository.getOrdersStatisticsByYear(year);
    }
}