package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.AssignmentCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.AssignmentResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.AssignmentTourGuideResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AssignmentService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
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

@RestController
@RequestMapping("/assignments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AssignmentController {
    AssignmentService assignmentService;
    OrderService orderService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<AssignmentResponse>> createAssignment(@RequestBody @Valid AssignmentCreationRequest request) {
        ApiResponse<AssignmentResponse> apiResponse = ApiResponse.<AssignmentResponse>builder()
                .code(1979)
                .message("Thêm lịch phân công mới thành công.")
                .result(assignmentService.createAssignment(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<AssignmentResponse>>> getAssignments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("startDate")));
        Page<AssignmentResponse> assignmentsPage = assignmentService.getAssignments(pageable);

        ApiResponse<Page<AssignmentResponse>> apiResponse = ApiResponse.<Page<AssignmentResponse>>builder()
                .code(1978)
                .result(assignmentsPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<AssignmentTourGuideResponse>> getAssignmentById(@PathVariable String id) {
        ApiResponse<AssignmentTourGuideResponse> apiResponse = ApiResponse.<AssignmentTourGuideResponse>builder()
                .code(1973)
                .result(assignmentService.getAssignmentById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/tour/{id}")
    ResponseEntity<ApiResponse<Page<AssignmentGuideResponse>>> getGuidesByTourId(
            @PathVariable String id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Page<AssignmentGuideResponse> pagedResult = assignmentService.getGuideListByTourId(id, page, size);

        ApiResponse<Page<AssignmentGuideResponse>> apiResponse = ApiResponse.<Page<AssignmentGuideResponse>>builder()
                .code(1976)
                .result(pagedResult)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteAssignment(@PathVariable String id) {
        if (orderService.existsByAssignmentId(id)) {
            throw new AppException(ErrorCode.ORDER_PROCESSING);
        }

        assignmentService.deleteAssignment(id);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1975);
        apiResponse.setMessage("Xóa lịch phân công thành công.");

        return ResponseEntity.ok(apiResponse);
    }
}