package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.response.*;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.*;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderResponse;
import com.websitesaoviet.WebsiteSaoViet.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderController {
    OrderService orderService;
    AuthenticationService authenticationService;
    TourService tourService;
    GuideService guideService;
    UserService userService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<List<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>>> getOrders() {
        ApiResponse<List<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>> apiResponse = ApiResponse.<List<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>>builder()
                .code(1958)
                .result(orderService.getOrders())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/list")
    ResponseEntity<ApiResponse<List<OrderResponse>>> getOrdersByUserId(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        String id = authenticationService.getUserIdByToken(token);

        ApiResponse<List<OrderResponse>> apiResponse = ApiResponse.<List<OrderResponse>>builder()
                .code(1957)
                .result(orderService.getOrdersByUserId(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>> getOrderById(@PathVariable String id) {
        ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse> apiResponse = ApiResponse.<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>builder()
                .code(1956)
                .result(orderService.getOrderById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PostMapping("/cancel/{id}")
    ResponseEntity<ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>> cancelOrder(@PathVariable String id) {
        orderService.cancelOrder(id);


        ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse> apiResponse = ApiResponse.<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>builder()
                .code(1955)
                .message("Hủy lịch đặt thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirm/{id}")
    ResponseEntity<ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>> confirmOrder(@PathVariable String id) {
        orderService.confirmOrder(id);

        ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse> apiResponse = ApiResponse.<com.websitesaoviet.WebsiteSaoViet.dto.response.OrderResponse>builder()
                .code(1954)
                .message("Xác nhận lịch đặt thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    ResponseEntity<ApiResponse<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderResponse>>> getAllOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderResponse> ordersPage = orderService.getAllOrders(pageable);

        ApiResponse<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderResponse>> apiResponse = ApiResponse.<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderResponse>>builder()
                .code(1953)
                .result(ordersPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/detail/{id}")
    ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderDetail(@PathVariable String id) {
        ApiResponse<OrderDetailResponse> apiResponse = ApiResponse.<OrderDetailResponse>builder()
                .code(1952)
                .result(orderService.getOrderDetail(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/info")
    ResponseEntity<ApiResponse<HomeResponse>> getAllInfo() {
        long quantityTour = tourService.countTours();
        long quantityGuide = guideService.countGuides();
        long quantityUser = userService.countUsers();
        long quantityOrder = orderService.countOrders();
        long quantityTotalPrice = orderService.getTotalRevenue();

        HomeResponse adminHomeResponse = new HomeResponse(
                quantityTour, quantityGuide, quantityUser, quantityOrder, quantityTotalPrice);
        ApiResponse<HomeResponse> apiResponse = ApiResponse.<HomeResponse>builder()
                .code(1951)
                .result(adminHomeResponse)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/latest")
    ResponseEntity<ApiResponse<List<LatestOrdersResponse>>> getLatestOrders() {
        ApiResponse<List<LatestOrdersResponse>> apiResponse = ApiResponse.<List<LatestOrdersResponse>>builder()
                .code(1950)
                .result(orderService.getLatestOrders())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/counts")
    ResponseEntity<ApiResponse<OrderStatusResponse>> getOrderStatusCounts() {
        ApiResponse<OrderStatusResponse> apiResponse = ApiResponse.<OrderStatusResponse>builder()
                .code(2959)
                .result(orderService.getOrderStatusCounts())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/statistics")
    ResponseEntity<ApiResponse<List<OrderStatisticsResponse>>> getOrdersStatisticsByMonth() {
        ApiResponse<List<OrderStatisticsResponse>> apiResponse = ApiResponse.<List<OrderStatisticsResponse>>builder()
                .code(2958)
                .result(orderService.getOrdersStatisticsByMonth())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}