package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.BookingResponse;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import com.websitesaoviet.WebsiteSaoViet.service.BookingService;
import com.websitesaoviet.WebsiteSaoViet.service.CustomerService;
import com.websitesaoviet.WebsiteSaoViet.service.TourService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class BookingController {
    BookingService bookingService;
    AuthenticationService authenticationService;
    TourService tourService;
    CustomerService customerService;

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping()
//    ResponseEntity<ApiResponse<List<BookingResponse>>> getBookings() {
//        ApiResponse<List<BookingResponse>> apiResponse = ApiResponse.<List<BookingResponse>>builder()
//                .code(1800)
//                .result(bookingService.getBookings())
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @GetMapping("/list")
//    ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByCustomerId(@RequestHeader("Authorization") String authorizationHeader)
//            throws ParseException, JOSEException {
//        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
//        String id = authenticationService.getCustomerIdByToken(token);
//
//        ApiResponse<List<BookingResponse>> apiResponse = ApiResponse.<List<BookingResponse>>builder()
//                .code(1801)
//                .result(bookingService.getBookingsByCustomerId(id))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @GetMapping("/{id}")
//    ResponseEntity<ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.BookingResponse>> getBookingById(@PathVariable String id) {
//        ApiResponse<com.websitesaoviet.WebsiteSaoViet.dto.response.BookingResponse> apiResponse = ApiResponse.<com.websitesaoviet.WebsiteSaoViet.dto.response.BookingResponse>builder()
//                .code(1802)
//                .result(bookingService.getBookingById(id))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
    @PatchMapping("/cancel/{id}")
    ResponseEntity<ApiResponse<BookingResponse>> cancelBooking(@PathVariable String id) {
        bookingService.cancelBooking(id);

        ApiResponse<BookingResponse> apiResponse = ApiResponse.<BookingResponse>builder()
                .code(1803)
                .message("Hủy lịch đặt thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/confirm/{id}")
    ResponseEntity<ApiResponse<BookingResponse>> confirmBooking(@PathVariable String id) {
        bookingService.confirmBooking(id);

        ApiResponse<BookingResponse> apiResponse = ApiResponse.<BookingResponse>builder()
                .code(1804)
                .message("Xác nhận lịch đặt thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/reserve/{id}")
    ResponseEntity<ApiResponse<BookingResponse>> confirmReserve(@PathVariable String id) {
        bookingService.confirmReserve(id);

        ApiResponse<BookingResponse> apiResponse = ApiResponse.<BookingResponse>builder()
                .code(1805)
                .message("Xác nhận giữ chỗ thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/all")
//    ResponseEntity<ApiResponse<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.BookingResponse>>> getAllBookings(
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "6") int size) {
//
//        Pageable pageable = PageRequest.of(page, size);
//        Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.BookingResponse> bookingsPage = bookingService.getAllBookings(pageable);
//
//        ApiResponse<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.BookingResponse>> apiResponse = ApiResponse.<Page<com.websitesaoviet.WebsiteSaoViet.dto.response.admin.BookingResponse>>builder()
//                .code(1805)
//                .result(bookingsPage)
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/detail/{id}")
//    ResponseEntity<ApiResponse<BookingDetailResponse>> getBookingDetail(@PathVariable String id) {
//        ApiResponse<BookingDetailResponse> apiResponse = ApiResponse.<BookingDetailResponse>builder()
//                .code(1806)
//                .result(bookingService.getBookingDetail(id))
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/info")
//    ResponseEntity<ApiResponse<HomeResponse>> getAllInfo() {
//        long quantityTour = tourService.countTours();
//        long quantityGuide = guideService.countGuides();
//        long quantityCustomer = customerService.countCustomers();
//        long quantityBooking = bookingService.countBookings();
//        long quantityTotalPrice = bookingService.getTotalRevenue();
//
//        HomeResponse adminHomeResponse = new HomeResponse(
//                quantityTour, quantityGuide, quantityCustomer, quantityBooking, quantityTotalPrice);
//        ApiResponse<HomeResponse> apiResponse = ApiResponse.<HomeResponse>builder()
//                .code(1800)
//                .result(adminHomeResponse)
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/latest")
//    ResponseEntity<ApiResponse<List<LatestBookingsResponse>>> getLatestBookings() {
//        ApiResponse<List<LatestBookingsResponse>> apiResponse = ApiResponse.<List<LatestBookingsResponse>>builder()
//                .code(1800)
//                .result(bookingService.getLatestBookings())
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/counts")
//    ResponseEntity<ApiResponse<BookingStatusResponse>> getBookingStatusCounts() {
//        ApiResponse<BookingStatusResponse> apiResponse = ApiResponse.<BookingStatusResponse>builder()
//                .code(2959)
//                .result(bookingService.getBookingStatusCounts())
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
//
//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/statistics")
//    ResponseEntity<ApiResponse<List<BookingStatisticsResponse>>> getBookingsStatisticsByMonth() {
//        ApiResponse<List<BookingStatisticsResponse>> apiResponse = ApiResponse.<List<BookingStatisticsResponse>>builder()
//                .code(2958)
//                .result(bookingService.getBookingsStatisticsByMonth())
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
}