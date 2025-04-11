package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.CheckoutSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Checkout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface CheckoutRepository extends JpaRepository<Checkout, String> {
    boolean existsCheckoutByCode(String code);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.CheckoutSummaryResponse(" +
            "c.code, c.bookingId, b.isReserved) " +
            "FROM Checkout c " +
            "INNER JOIN Booking b ON c.bookingId = b.id " +
            "WHERE c.id = :id AND c.status = 'Chưa thanh toán' AND b.status = 'Đang xử lý'")
    CheckoutSummaryResponse findCheckoutValidById(@Param("id") String id);
}