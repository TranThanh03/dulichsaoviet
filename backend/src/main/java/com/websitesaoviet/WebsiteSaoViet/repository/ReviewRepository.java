package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse(" +
            "r.id, r.rating, r.comment, r.timeStamp, c.fullName, " +
            "CASE WHEN r.customerId = :customerId THEN true ELSE false END) " +
            "FROM Review r " +
            "LEFT JOIN Customer c ON r.customerId = c.id " +
            "WHERE r.tourId = :tourId " +
            "ORDER BY CASE WHEN r.customerId = :customerId THEN 0 ELSE 1 END, r.timeStamp DESC")
    List<ReviewResponse> findAllByTourIdWithCustomer(@Param("tourId") String tourId, @Param("customerId") String customerId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse(" +
            "r.id, r.rating, r.comment, r.timeStamp, c.fullName, false) " +
            "FROM Review r " +
            "LEFT JOIN Customer c ON r.customerId = c.id " +
            "WHERE r.tourId = :tourId " +
            "ORDER BY r.timeStamp DESC")
    List<ReviewResponse> findAllByTourId(@Param("tourId") String tourId);

    boolean existsReviewByIdAndCustomerId(String id, String customerId);
}