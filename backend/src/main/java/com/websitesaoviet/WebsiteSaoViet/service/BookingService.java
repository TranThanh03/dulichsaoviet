package com.websitesaoviet.WebsiteSaoViet.service;

import com.websitesaoviet.WebsiteSaoViet.dto.response.common.BookingResponse;
import com.websitesaoviet.WebsiteSaoViet.entity.Booking;
import com.websitesaoviet.WebsiteSaoViet.enums.BookingStatus;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.mapper.BookingMapper;
import com.websitesaoviet.WebsiteSaoViet.repository.BookingRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class BookingService {
    BookingRepository bookingRepository;
    BookingMapper bookingMapper;
    CustomerService customerService;
    ScheduleService scheduleService;
    TourService tourService;

    public BookingResponse createBooking (String bookingCode, String customerId, String scheduleId,
                                          int quantityAdult, int quantityChildren,
                                          Double amount, Double discount) {
        Booking booking = new Booking();
        var customer = customerService.getCustomerById(customerId);
        var schedule = scheduleService.getScheduleById(scheduleId);
        var tour = tourService.getTourById(schedule.getTourId());
        LocalDateTime currentTime = LocalDateTime.now();

        booking.setCode(bookingCode);

        booking.setCustomerId(customerId);
        booking.setCustomerCode(customer.getCode());

        booking.setTourId(schedule.getTourId());
        booking.setTourCode(tour.getCode());
        booking.setTourName(tour.getName());
        booking.setQuantityDay(tour.getQuantityDay());

        booking.setScheduleId(scheduleId);
        booking.setScheduleCode(schedule.getCode());
        booking.setStartDate(schedule.getStartDate());
        booking.setEndDate(schedule.getEndDate());
        booking.setAdultPrice(schedule.getAdultPrice());
        booking.setChildrenPrice(schedule.getChildrenPrice());

        booking.setQuantityAdult(quantityAdult);
        booking.setQuantityChildren(quantityChildren);
        booking.setDiscount(discount);
        booking.setTotalPrice(amount);
        booking.setBookingTime(currentTime);
        booking.setStatus(BookingStatus.PROCESSING.getValue());

        return bookingMapper.toBookingResponse(bookingRepository.save(booking));
    }

//    public Page<BookingResponse> getBookings(Pageable pageable) {
//        return bookingRepository.findBookings(pageable);
//    }
//
//    public List<BookingResponse> getBookingsByCustomerId(String id) {
//        return bookingRepository.findBookingListByCustomerId(id);
//    }
//
//    public BookingResponse getBookingById(String id) {
//        return  bookingMapper.toBookingResponse(bookingRepository.findById(id)
//                .orElseThrow(() -> new AppException(ErrorCode.ORDER_NOT_EXITED)));
//    }
//
//    public BookingDetailResponse getBookingDetail(String id) {
//        return bookingRepository.getBookingDetail(id);
//    }
//
//    public BookingCheckoutResponse getCheckoutByBookingId(String id) {
//        return bookingRepository.getCheckoutByBookingId(id);
//    }
//
//    @Transactional
//    public void cancelBooking(String id) {
//        var booking = bookingRepository.existsBookingProcessing(id);
//
//        if (booking == null) {
//            throw new AppException(ErrorCode.INVALID_ORDER);
//        }
//
//        booking.setStatus("Đã hủy");
//        bookingRepository.save(booking);
//
//        var schedule = bookingRepository.getScheduleByBookingId(id);
//
//        if(schedule != null) {
//            scheduleService.minusNumberOfPeople(schedule.getAnotherId(), schedule.getNumberOfPeople());
//        }
//    }
//
//    public void confirmBooking(String id) {
//        var booking = bookingRepository.existsBookingsPaid(id);
//
//        if (booking == null) {
//            throw new AppException(ErrorCode.INVALID_ORDER);
//        }
//
//        var tour = bookingRepository.getTourByBookingId(id);
//        booking.setStatus("Đã xác nhận");
//
//        if(tour != null) {
//            tourService.addOders(tour.getAnotherId(), tour.getNumberOfPeople());
//        }
//
//        bookingRepository.save(booking);
//    }
//
//    public long countBookings() {
//        return bookingRepository.countBookings();
//    }
//
//    public long getTotalRevenue() {
//        return bookingRepository.getTotalRevenue();
//    }
//
//    public List<LatestBookingsResponse> getLatestBookings() {
//        return bookingRepository.getLatestBookings();
//    }
//
//    public boolean existsByCustomerId(String customerId) {
//        return bookingRepository.existsByCustomerId(customerId);
//    }
//
//    public void deleteByCustomerId(String customerId) {
//        bookingRepository.deleteByCustomerId(customerId);
//    }
//
//    public boolean existsByScheduleId(String scheduleId) {
//        return bookingRepository.existsByScheduleId(scheduleId);
//    }
//
//    public boolean existsByTourId(String tourId) {
//        return bookingRepository.existsByTourId(tourId);
//    }
//
//    public boolean existsByGuideId(String guideId) {
//        return bookingRepository.existsByGuideId(guideId);
//    }
//
//    public BookingStatusResponse getBookingStatusCounts() {
//        return bookingRepository.getBookingStatusCounts();
//    }
//
//    public List<BookingStatisticsResponse> getBookingsStatisticsByMonth() {
//        int year = LocalDate.now().getYear();
//
//        return bookingRepository.getBookingsStatisticsByYear(year);
//    }
}