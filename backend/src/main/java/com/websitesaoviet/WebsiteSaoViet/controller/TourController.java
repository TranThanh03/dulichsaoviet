package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.TourCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.TourUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AssignmentToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.PopularToursResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.TourResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.CategoryResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.TourNewResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AssignmentService;
import com.websitesaoviet.WebsiteSaoViet.service.CategoryService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
import com.websitesaoviet.WebsiteSaoViet.service.TourService;
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
@RequestMapping("/tours")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TourController {
    TourService tourService;
    CategoryService categoryService;
    OrderService orderService;
    AssignmentService assignmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<TourResponse>> createTour(@RequestBody TourCreationRequest request) {
        ApiResponse<TourResponse> apiResponse = ApiResponse.<TourResponse>builder()
                .code(1989)
                .message("Thêm tour mới thành công.")
                .result(tourService.createTour(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    ResponseEntity<ApiResponse<Page<TourResponse>>> getTours(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("orders"), Sort.Order.desc("timeCreated")));
        Page<TourResponse> toursPage = tourService.getTours(pageable);

        ApiResponse<Page<TourResponse>> apiResponse = ApiResponse.<Page<TourResponse>>builder()
                .code(1988)
                .result(toursPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/assignment")
    ResponseEntity<ApiResponse<List<AssignmentToursResponse>>> getToursByAssignment() {
        ApiResponse<List<AssignmentToursResponse>> apiResponse = ApiResponse.<List<AssignmentToursResponse>>builder()
                .code(2989)
                .result(tourService.getToursByAssignment())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/new")
    ResponseEntity<ApiResponse<List<TourNewResponse>>> getToursNew() {
        ApiResponse<List<TourNewResponse>> apiResponse = ApiResponse.<List<TourNewResponse>>builder()
                .code(1987)
                .result(tourService.getToursNew())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/category")
    ResponseEntity<ApiResponse<List<CategoryResponse>>> getTourCategory() {
        ApiResponse<List<CategoryResponse>> apiResponse = ApiResponse.<List<CategoryResponse>>builder()
                .code(1986)
                .result(categoryService.getTourCategory())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<TourResponse>> getTourById(@PathVariable String id) {
        ApiResponse<TourResponse> apiResponse = ApiResponse.<TourResponse>builder()
                .code(1985)
                .result(tourService.getTourById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/category/{id}")
    ResponseEntity<ApiResponse<Page<TourResponse>>> getTourByCategoryId(
            @PathVariable int id,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("orders"), Sort.Order.desc("timeCreated")));
        Page<TourResponse> toursPage = tourService.getTourByCategoryId(id, pageable);

        ApiResponse<Page<TourResponse>> apiResponse = ApiResponse.<Page<TourResponse>>builder()
                .code(1984)
                .result(toursPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<TourResponse>> updateTour(@PathVariable String id, @RequestBody TourUpdateRequest request) {
        ApiResponse<TourResponse> apiResponse = ApiResponse.<TourResponse>builder()
                .code(1983)
                .message("Cập nhật thông tin tour thành công.")
                .result(tourService.updateTour(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteTour(@PathVariable String id) {
        if (orderService.existsByTourId(id)) {
            throw new AppException(ErrorCode.ORDER_PROCESSING);
        }

        assignmentService.deleteByTourId(id);
        tourService.deleteTour(id);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1982);
        apiResponse.setMessage("Xóa tour thành công.");

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/search")
    ResponseEntity<ApiResponse<Page<TourResponse>>> searchTours(
            @RequestParam String p,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("orders"), Sort.Order.desc("timeCreated")));
        Page<TourResponse> toursPage = tourService.searchTours(p, pageable);

        ApiResponse<Page<TourResponse>> apiResponse = ApiResponse.<Page<TourResponse>>builder()
                .code(1981)
                .result(toursPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/popular")
    ResponseEntity<ApiResponse<List<PopularToursResponse>>> getPopularTours() {
        ApiResponse<List<PopularToursResponse>> apiResponse = ApiResponse.<List<PopularToursResponse>>builder()
                .code(1980)
                .result(tourService.getPopularTours())
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}