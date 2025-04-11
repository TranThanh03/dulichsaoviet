package com.websitesaoviet.WebsiteSaoViet.repository;

import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

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
}