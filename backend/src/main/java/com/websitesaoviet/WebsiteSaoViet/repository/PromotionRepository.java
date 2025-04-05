package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.PromotionSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.PromotionSummaryResponse(" +
            "p.id, p.code, p.title, p.description, p.discount, p.endDate, p.quantity) " +
            "FROM Promotion p " +
            "WHERE p.status = 'Đang diễn ra' " +
            "ORDER BY p.endDate ASC" )
    List<PromotionSummaryResponse> findPromotionList();

    @Query("SELECT p " +
            "FROM Promotion p " +
            "WHERE p.id = :id AND p.status = 'Đang diễn ra' AND p.quantity > 0")
    Promotion findAvailablePromotionById(@Param("id") String id);

    @Transactional
    @Modifying
    @Query("UPDATE Promotion p SET p.quantity = p.quantity - :quantity WHERE p.id = :id")
    void minusQuantity(@Param("id") String id, @Param("quantity") int quantity);
}