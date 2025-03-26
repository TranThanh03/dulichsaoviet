package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.GuideCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.GuideUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.AssignmentGuidesResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.GuideEvaluateResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.GuideResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AssignmentService;
import com.websitesaoviet.WebsiteSaoViet.service.GuideService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
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
@RequestMapping("/api/guides")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class GuideController {
    GuideService guideService;
    OrderService orderService;
    AssignmentService assignmentService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping()
    ResponseEntity<ApiResponse<GuideResponse>> createGuide(@RequestBody GuideCreationRequest request) {
        ApiResponse<GuideResponse> apiResponse = ApiResponse.<GuideResponse>builder()
                .code(1969)
                .message("Thêm hướng dẫn viên mới thành công.")
                .result(guideService.createGuide(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<Page<GuideResponse>>> getGuides(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("evaluate").descending());
        Page<GuideResponse> guidesPage = guideService.getGuides(pageable);

        ApiResponse<Page<GuideResponse>> apiResponse = ApiResponse.<Page<GuideResponse>>builder()
                .code(1968)
                .result(guidesPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/assignment")
    public ResponseEntity<ApiResponse<List<AssignmentGuidesResponse>>> getGuidesByAssignment() {
        ApiResponse<List<AssignmentGuidesResponse>> apiResponse = ApiResponse.<List<AssignmentGuidesResponse>>builder()
                .code(1963)
                .result(guideService.getGuidesByAssignment())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/evaluate")
    ResponseEntity<ApiResponse<List<GuideEvaluateResponse>>> getGuidesEvaluate() {
        ApiResponse<List<GuideEvaluateResponse>> apiResponse = ApiResponse.<List<GuideEvaluateResponse>>builder()
                .code(1967)
                .result(guideService.getGuidesEvaluate())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<GuideResponse>> getGuideById(@PathVariable String id) {
        ApiResponse<GuideResponse> apiResponse = ApiResponse.<GuideResponse>builder()
                .code(1966)
                .result(guideService.getGuideById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<GuideResponse>> updateGuide(@PathVariable String id, @RequestBody GuideUpdateRequest request) {
        ApiResponse<GuideResponse> apiResponse = ApiResponse.<GuideResponse>builder()
                .code(1965)
                .message("Cập nhật thông tin hướng dẫn viên thành công.")
                .result(guideService.updateGuide(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteGuide(@PathVariable String id) {
        if (orderService.existsByGuideId(id)) {
            throw new AppException(ErrorCode.ORDER_PROCESSING);
        }

        assignmentService.deleteByGuideId(id);
        guideService.deleteGuide(id);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1964);
        apiResponse.setMessage("Xóa hướng dẫn viên thành công.");

        return ResponseEntity.ok(apiResponse);
    }
}