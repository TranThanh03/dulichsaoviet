package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.*;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderDetailResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderPaymentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderListResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderListResponse(" +
            "o.id, o.orderCode, o.tourId, o.tourName, t.image, " +
            "o.guideId, g.fullName, o.startDate, o.endDate, " +
            "o.numberOfPeople, o.totalPrice, o.orderDatetime, o.status, " +
            "p.method, p.status) " +
            "FROM Order o " +
            "LEFT JOIN Payment p ON o.id = p.orderId " +
            "LEFT JOIN Tour t ON o.tourId = t.id " +
            "LEFT JOIN Guide g ON o.guideId = g.id " +
            "WHERE o.customerId = :customerId " +
            "ORDER BY (CASE WHEN o.status = 'Đang xử lý' THEN 1 ELSE 2 END), o.orderDatetime DESC")
    List<OrderListResponse> findOrderListByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderSummaryResponse(" +
            "o.assignmentId, o.numberOfPeople) " +
            "FROM Order o " +
            "INNER JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.id = :id")
    OrderSummaryResponse getAssignmentByOrderId(@Param("id") String id);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderSummaryResponse(" +
            "o.tourId, o.numberOfPeople) " +
            "FROM Order o " +
            "INNER JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.id = :id")
    OrderSummaryResponse getTourByOrderId(@Param("id") String id);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.OrderPaymentResponse(" +
            "o.assignmentId, o.numberOfPeople, p.id) " +
            "FROM Order o " +
            "INNER JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.id = :id")
    OrderPaymentResponse getPaymentByOrderId(@Param("id") String id);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderListResponse(" +
            "o.id, o.orderCode, o.customerCode, o.tourCode, " +
            "o.guideCode, o.assignmentCode, o.totalPrice, p.status, o.orderDatetime, o.status) " +
            "FROM Order o " +
            "LEFT JOIN Payment p ON o.id = p.orderId " +
            "ORDER BY (CASE WHEN o.status = 'Đang xử lý' THEN 1 ELSE 2 END), o.orderDatetime DESC")
    Page<OrderListResponse> getAllOrders(Pageable pageable);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.common.OrderDetailResponse(" +
            "o.id, o.orderCode, o.customerCode, c.fullName, c.phone, c.email, " +
            "o.tourCode, o.tourName, t.image, " +
            "o.guideCode, g.fullName, g.avatar, " +
            "o.startDate, o.endDate, o.tourPrice, o.guidePrice, o.numberOfPeople, " +
            "o.totalPrice, o.orderDatetime, o.status, " +
            "p.paymentCode, p.amount, p.method, p.paymentDatetime, p.status) " +
            "FROM Order o " +
            "LEFT JOIN Customer c ON o.customerId = c.id " +
            "LEFT JOIN Tour t ON o.tourId = t.id " +
            "LEFT JOIN Guide g ON o.guideId = g.id " +
            "LEFT JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.id = :id")
    OrderDetailResponse getOrderDetail(@Param("id") String id);

    @Query("SELECT o " +
            "FROM Order o " +
            "WHERE o.id = :id AND o.status = 'Đang xử lý'")
    Order existsOrderProcessing(@Param("id") String id);

    @Query("SELECT o " +
            "FROM Order o " +
            "INNER JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.id = :id AND o.status = 'Đang xử lý'")
    Order existsOrdersPaid(@Param("id") String id);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.status = 'Đã xác nhận'")
    long countOrders();

    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o WHERE o.status = 'Đã xác nhận'")
    long getTotalRevenue();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestOrdersResponse(" +
            "o.orderCode, o.customerCode, o.tourName, o.orderDatetime, o.totalPrice) " +
            "FROM Order o " +
            "WHERE o.status = 'Đang xử lý' " +
            "ORDER BY o.orderDatetime DESC " +
            "LIMIT 5")
    List<LatestOrdersResponse> getLatestOrders();

    @Query("SELECT COUNT(o) > 0 " +
            "FROM Order o " +
            "INNER JOIN Payment p ON o.id = p.orderId " +
            "WHERE o.customerId = :customerId AND o.status = 'Đang xử lý'")
    boolean existsByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT COUNT(o) > 0 " +
            "FROM Order o " +
            "WHERE o.assignmentId = :assignmentId AND o.status = 'Đang xử lý'")
    boolean existsByAssignmentId(@Param("assignmentId") String assignmentId);

    @Query("SELECT COUNT(o) > 0 " +
            "FROM Order o " +
            "WHERE o.tourId = :tourId AND o.status = 'Đang xử lý'")
    boolean existsByTourId(@Param("tourId") String tourId);

    @Query("SELECT COUNT(o) > 0 " +
            "FROM Order o " +
            "WHERE o.guideId = :guideId AND o.status = 'Đang xử lý'")
    boolean existsByGuideId(@Param("guideId") String guideId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderStatusResponse(" +
            "SUM(CASE WHEN o.status = 'Đang xử lý' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN o.status = 'Đã hủy' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN o.status = 'Đã xác nhận' THEN 1 ELSE 0 END)) " +
            "FROM Order o")
    OrderStatusResponse getOrderStatusCounts();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.OrderStatisticsResponse(" +
            "MONTH(o.orderDatetime), " +
            "COUNT(CASE WHEN o.status = 'Đã hủy' THEN 1 END), " +
            "COUNT(CASE WHEN o.status = 'Đã xác nhận' THEN 1 END), " +
            "COALESCE(SUM(CASE WHEN o.status = 'Đã xác nhận' THEN o.totalPrice ELSE 0 END), 0)) " +
            "FROM Order o " +
            "WHERE YEAR(o.orderDatetime) = :year " +
            "GROUP BY MONTH(o.orderDatetime) " +
            "ORDER BY MONTH(o.orderDatetime)")
    List<OrderStatisticsResponse> getOrdersStatisticsByYear(@Param("year") int year);
}