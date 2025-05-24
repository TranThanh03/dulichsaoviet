package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.HotTour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HotTourRepository extends JpaRepository<HotTour, String> {
    @Query("SELECT h FROM HotTour h")
    HotTour findSingleHotTour();
}