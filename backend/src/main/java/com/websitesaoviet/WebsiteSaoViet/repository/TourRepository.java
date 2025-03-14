package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.PopularToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.TourNewResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    @Query("SELECT MAX(t.id) FROM Tour t")
    String findMaxId();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.TourNewResponse(" +
            "t.id, t.name, t.image, t.price, t.orders) FROM Tour t ORDER BY t.timeCreated DESC LIMIT 9")
    List<TourNewResponse> getTourListNew();

    Page<Tour> findToursByCategoryId(int id, Pageable pageable);

    @Modifying
    @Query("UPDATE Tour t SET t.orders = t.orders + :people WHERE t.id = :id")
    void addOrders(@Param("id") String id, @Param("people") int people);

    Page<Tour> findByNameContainingIgnoreCaseOrderByOrdersDesc(String search, Pageable pageable);

    long count();

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.admin.PopularToursResponse(" +
            "t.name, t.orders) " +
            "FROM Tour t " +
            "ORDER BY t.orders DESC " +
            "LIMIT 5")
    List<PopularToursResponse> getPopularTours();
}