package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.ReviewUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.ReviewResponse;
import com.websitesaoviet.WebsiteSaoViet.service.ReviewService;
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
@RequestMapping("/reviews")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class ReviewController {
    ReviewService reviewService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<ReviewResponse>> createReview(@RequestBody @Valid ReviewCreationRequest request) {
        ApiResponse<ReviewResponse> apiResponse = ApiResponse.<ReviewResponse>builder()
                .code(2000)
                .message("Thêm review mới thành công.")
                .result(reviewService.createReview(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    ResponseEntity<ApiResponse<Page<ReviewResponse>>> getReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("startDate")));
        Page<ReviewResponse> reviewsPage = reviewService.getReviews(pageable);

        ApiResponse<Page<ReviewResponse>> apiResponse = ApiResponse.<Page<ReviewResponse>>builder()
                .code(2001)
                .result(reviewsPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<ReviewResponse>> getReviewById(@PathVariable String id) {
        ApiResponse<ReviewResponse> apiResponse = ApiResponse.<ReviewResponse>builder()
                .code(2002)
                .result(reviewService.getReviewById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<ReviewResponse>> updateUser(@PathVariable String id, @RequestBody @Valid ReviewUpdateRequest request) {
        ApiResponse<ReviewResponse> apiResponse = ApiResponse.<ReviewResponse>builder()
                .code(2003)
                .message("Cập nhật thông tin review thành công.")
                .result(reviewService.updateReview(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteReview(@PathVariable String id) {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(2004)
                .message("Xóa review thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}