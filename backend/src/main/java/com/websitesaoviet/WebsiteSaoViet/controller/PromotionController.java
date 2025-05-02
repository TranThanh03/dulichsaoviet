package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.PromotionCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.PromotionUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.PromotionResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.PromotionSummaryResponse;
import com.websitesaoviet.WebsiteSaoViet.service.PromotionService;
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
@RequestMapping("/promotions")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class PromotionController {
    PromotionService promotionService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<PromotionResponse>> createPromotion(@RequestBody @Valid PromotionCreationRequest request) {
        ApiResponse<PromotionResponse> apiResponse = ApiResponse.<PromotionResponse>builder()
                .code(1700)
                .message("Thêm khuyến mãi mới thành công.")
                .result(promotionService.createPromotion(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<PromotionResponse>>> getPromotions(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("startDate")));
        Page<PromotionResponse> promotionsPage = promotionService.getPromotions(pageable);

        ApiResponse<Page<PromotionResponse>> apiResponse = ApiResponse.<Page<PromotionResponse>>builder()
                .code(1701)
                .result(promotionsPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/list")
    ResponseEntity<ApiResponse<List<PromotionSummaryResponse>>> getPromotionList() {
        ApiResponse<List<PromotionSummaryResponse>> apiResponse = ApiResponse.<List<PromotionSummaryResponse>>builder()
                .code(1702)
                .result(promotionService.getPromotionList())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<PromotionResponse>> getPromotionById(@PathVariable String id) {
        ApiResponse<PromotionResponse> apiResponse = ApiResponse.<PromotionResponse>builder()
                .code(1703)
                .result(promotionService.getPromotionById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<PromotionResponse>> updateUser(@PathVariable String id, @RequestBody @Valid PromotionUpdateRequest request) {
        ApiResponse<PromotionResponse> apiResponse = ApiResponse.<PromotionResponse>builder()
                .code(1704)
                .message("Cập nhật thông tin khuyến mãi thành công.")
                .result(promotionService.updatePromotion(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deletePromotion(@PathVariable String id) {

        promotionService.deletePromotion(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1705)
                .message("Xóa khuyến mãi thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}