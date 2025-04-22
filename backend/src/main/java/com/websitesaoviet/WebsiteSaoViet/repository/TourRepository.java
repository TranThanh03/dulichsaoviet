package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Query(value = """
    SELECT t.id, t.name, t.destination,
        (SELECT i.image FROM tour_images i WHERE i.tour_id = t.id LIMIT 1) AS image,
        t.quantity_day,
        MIN(s.adult_price) AS adult_price,
        SUM(s.total_people - s.quantity_people) AS people,
        IFNULL(FLOOR(AVG(r.rating)), 0) AS rating,
        MAX(s.created_time) AS created_time
    FROM tour t
    LEFT JOIN schedule s ON s.tour_id = t.id
    LEFT JOIN review r ON r.tour_id = t.id
    WHERE
        (s.status = 'Chưa diễn ra') AND
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
    LEFT JOIN schedule s ON s.tour_id = t.id
    LEFT JOIN review r ON r.tour_id = t.id
    WHERE
        (s.status = 'Chưa diễn ra') AND
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
}