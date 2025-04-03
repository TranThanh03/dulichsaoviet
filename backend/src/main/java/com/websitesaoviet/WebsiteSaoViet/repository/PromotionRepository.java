package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.PromotionSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.PromotionSummaryResponse(" +
            "p.id, p.code, p.title, p.description, p.discount, p.endDate, p.quantity) " +
            "FROM Promotion p " +
            "WHERE p.status = 'Đang diễn ra' " +
            "ORDER BY p.endDate ASC" )
    List<PromotionSummaryResponse> findPromotionList();
}