package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.ScheduleCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ScheduleResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ScheduleTourResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.BookingService;
import com.websitesaoviet.WebsiteSaoViet.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ScheduleController {
    ScheduleService scheduleService;
    BookingService bookingService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<ScheduleResponse>> createSchedule(@RequestBody @Valid ScheduleCreationRequest request) {
        ApiResponse<ScheduleResponse> apiResponse = ApiResponse.<ScheduleResponse>builder()
                .code(1600)
                .message("Thêm lịch trình mới thành công.")
                .result(scheduleService.createSchedule(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<ScheduleResponse>>> getSchedules(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("startDate")));
        Page<ScheduleResponse> schedulesPage = scheduleService.getSchedules(pageable);

        ApiResponse<Page<ScheduleResponse>> apiResponse = ApiResponse.<Page<ScheduleResponse>>builder()
                .code(1601)
                .result(schedulesPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ScheduleResponse>> getScheduleById(@PathVariable String id) {
        ApiResponse<ScheduleResponse> apiResponse = ApiResponse.<ScheduleResponse>builder()
                .code(1602)
                .result(scheduleService.getScheduleById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/tour/{id}")
    public ResponseEntity<ApiResponse<List<ScheduleSummaryResponse>>> getSchedulesByTourId(@PathVariable String id) {
        ApiResponse<List<ScheduleSummaryResponse>> successResponse = ApiResponse.<List<ScheduleSummaryResponse>>builder()
                .code(1603)
                .result(scheduleService.getSchedulesByTourId(id))
                .build();

        return ResponseEntity.ok(successResponse);
    }

    @GetMapping("/schedule-tour/{id}")
    public ResponseEntity<ApiResponse<ScheduleTourResponse>> getScheduleTourById(@PathVariable String id) {
        ApiResponse<ScheduleTourResponse> successResponse = ApiResponse.<ScheduleTourResponse>builder()
                .code(1604)
                .result(scheduleService.getScheduleTourById(id))
                .build();

        return ResponseEntity.ok(successResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteSchedule(@PathVariable String id) {
        if (bookingService.existsByScheduleId(id)) {
            throw new AppException(ErrorCode.BOOKING_PROCESSING);
        }

        scheduleService.deleteSchedule(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1605)
                .message("Xóa lịch trình thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}