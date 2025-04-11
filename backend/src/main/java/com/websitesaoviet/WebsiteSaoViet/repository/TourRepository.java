package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Tour t SET t.quantityOrder = t.quantityOrder + :orders WHERE t.id = :id")
    void addOrders(@Param("id") String id, @Param("orders") int orders);
}