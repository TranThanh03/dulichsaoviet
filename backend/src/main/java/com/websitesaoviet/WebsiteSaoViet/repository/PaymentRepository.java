package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, String> {
    @Query("SELECT COUNT(p) > 0 " +
            "FROM Payment p WHERE p.paymentId = :id AND p.status = 'Đã thanh toán'")
    boolean existsPaymentById(@Param("id") String id);

    @Modifying
    void deleteByOrderId(@Param("id") String orderId);

    @Transactional
    @Modifying
    @Query("DELETE FROM Payment p WHERE p.orderId IN (SELECT o.id FROM Order o WHERE o.userId = :userId)")
    void deleteByUserId(@Param("userId") String userId);
}
