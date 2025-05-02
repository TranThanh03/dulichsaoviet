package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AreaTourCountResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface TourRepository extends JpaRepository<Tour, String> {
    @Transactional
    @Modifying
    @Query("UPDATE Tour t SET t.quantityOrder = t.quantityOrder + :orders WHERE t.id = :id")
    void addOrders(@Param("id") String id, @Param("orders") int orders);

    @Query(value = """
    SELECT t.id, t.name, t.destination,
        (SELECT i.image FROM tour_images i WHERE i.tour_id = t.id LIMIT 1) AS image,
        t.quantity_day,
        MIN(s.adult_price) AS adult_price,
        SUM(DISTINCT s.total_people - s.quantity_people) AS people,
        IFNULL(FLOOR(AVG(r.rating)), 0) AS rating,
        MAX(s.created_time) AS created_time
    FROM tour t
    INNER JOIN schedule s ON t.id = s.tour_id
    LEFT JOIN review r ON t.id = r.tour_id
    WHERE
        (s.status = 'Chưa diễn ra') AND
        (s.quantity_people < s.total_people) AND
        (:minPrice IS NULL OR s.adult_price >= :minPrice) AND
        (:maxPrice IS NULL OR s.adult_price <= :maxPrice) AND
        (:area IS NULL OR t.area = :area) AND
        (:quantityDay IS NULL OR t.quantity_day = :quantityDay)
    GROUP BY t.id, t.name, t.destination, t.quantity_day
    HAVING (:rating IS NULL OR FLOOR(AVG(r.rating)) = :rating)
    """,
            countQuery = """
    SELECT COUNT(DISTINCT t.id)
    FROM tour t
    INNER JOIN schedule s ON t.id = s.tour_id
    LEFT JOIN review r ON t.id = r.tour_id
    WHERE
        (s.status = 'Chưa diễn ra') AND
        (s.quantity_people < s.total_people) AND
        (:minPrice IS NULL OR s.adult_price >= :minPrice) AND
        (:maxPrice IS NULL OR s.adult_price <= :maxPrice) AND
        (:area IS NULL OR t.area = :area) AND
        (:quantityDay IS NULL OR t.quantity_day = :quantityDay)
    GROUP BY t.id
    HAVING (:rating IS NULL OR FLOOR(AVG(r.rating)) = :rating)
    """,
            nativeQuery = true)
    Page<Object[]> findFilteredToursNative(
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("area") String area,
            @Param("rating") Integer rating,
            @Param("quantityDay") Integer quantityDay,
            Pageable pageable
    );

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.AreaTourCountResponse(" +
            "SUM(CASE WHEN t.area = 'b' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN t.area = 't' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN t.area = 'n' THEN 1 ELSE 0 END)) " +
            "FROM Tour t")
    AreaTourCountResponse countToursByArea();

    @Query("SELECT t " +
            "FROM Tour t " +
            "ORDER BY t.quantityOrder DESC " +
            "LIMIT 5")
    List<Tour> findPopularTourResponse();
}