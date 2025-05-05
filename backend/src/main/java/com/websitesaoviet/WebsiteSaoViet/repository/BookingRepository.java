package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AreaTourCountResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.BookingDetailResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Booking, String> {
    Booking findBookingByIdAndStatus(String id, String status);

    @Query("SELECT b " +
            "FROM Booking b " +
            "INNER JOIN Checkout c ON b.id = c.bookingId " +
            "WHERE b.id = :id AND b.status = 'Đang xử lý' AND c.status = 'Đã thanh toán'")
    Booking findBookingPaid(@Param("id") String id);

    Booking findBookingByIdAndStatusAndIsReserved(String id, String status, boolean isReserved);

    @Query("SELECT COUNT(b) > 0 " +
            "FROM Booking b " +
            "WHERE b.customerId = :customerId AND b.status = 'Đang xử lý'")
    boolean existsByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT COUNT(b) > 0 " +
            "FROM Booking b " +
            "WHERE b.scheduleId = :scheduleId AND b.status = 'Đang xử lý'")
    boolean existsByScheduleId(@Param("scheduleId") String scheduleId);

    @Query("SELECT COUNT(b) > 0 " +
            "FROM Booking b " +
            "WHERE b.tourId = :tourId AND b.status = 'Đang xử lý'")
    boolean existsByTourId(@Param("tourId") String tourId);

    Booking findBookingByIdAndCustomerIdAndIsReviewed(String id, String customerId, boolean isReviewed);

    @Query(value = """
    SELECT b.id, b.code, b.tour_id, b.tour_name,
        (SELECT i.image FROM tour_images i WHERE i.tour_id = t.id LIMIT 1) AS image,
        t.destination, b.quantity_day, (b.quantity_adult + b.quantity_children) AS people, b.total_price,
        IFNULL(FLOOR(AVG(r.rating)), 0) AS rating,
        b.booking_time, b.status, b.is_reviewed, c.method
    FROM booking b
    INNER JOIN checkout c ON b.id = c.booking_id
    LEFT JOIN tour t ON b.tour_id = t.id
    LEFT JOIN review r ON b.tour_id = r.tour_id
    WHERE b.customer_id = :customerId
    GROUP BY b.id, c.id
    ORDER BY b.booking_time DESC
    """, nativeQuery = true)
    List<Object[]> findBookingsByCustomerId(@Param("customerId") String customerId);

    @Query("SELECT new com.websitesaoviet.WebsiteSaoViet.dto.response.user.BookingDetailResponse(" +
            "b.id, b.code, b.tourId, b.tourCode, b.tourName, b.startDate, b.endDate, b.quantityDay, " +
            "b.quantityAdult, b.quantityChildren, b.adultPrice, b.childrenPrice, " +
            "b.discount, b.totalPrice, b.bookingTime, b.status, b.isReviewed, " +
            "c.code, c.method, c.checkoutTime, c.status) " +
            "FROM Booking b " +
            "INNER JOIN Checkout c ON b.id = c.bookingId " +
            "WHERE b.id = :id")
    BookingDetailResponse findBookingDetail(@Param("id") String id);
}