package com.websitesaoviet.WebsiteSaoViet.controller;

import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.admin.NewsUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.NewsResponse;
import com.websitesaoviet.WebsiteSaoViet.service.NewsService;
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
@RequestMapping("/news")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class NewsController {
    NewsService newsService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("")
    ResponseEntity<ApiResponse<NewsResponse>> createNews(@RequestBody @Valid NewsCreationRequest request) {
        ApiResponse<NewsResponse> apiResponse = ApiResponse.<NewsResponse>builder()
                .code(2100)
                .message("Thêm tin tức mới thành công.")
                .result(newsService.createNews(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping()
    ResponseEntity<ApiResponse<Page<NewsResponse>>> getNewss(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("startDate")));
        Page<NewsResponse> newsPage = newsService.getNewss(pageable);

        ApiResponse<Page<NewsResponse>> apiResponse = ApiResponse.<Page<NewsResponse>>builder()
                .code(2101)
                .result(newsPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<NewsResponse>> getNewsById(@PathVariable String id) {
        ApiResponse<NewsResponse> apiResponse = ApiResponse.<NewsResponse>builder()
                .code(2102)
                .result(newsService.getNewsById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<NewsResponse>> updateUser(@PathVariable String id, @RequestBody @Valid NewsUpdateRequest request) {
        ApiResponse<NewsResponse> apiResponse = ApiResponse.<NewsResponse>builder()
                .code(2103)
                .message("Cập nhật thông tin tin tức thành công.")
                .result(newsService.updateNews(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteNews(@PathVariable String id){
        newsService.deleteNews(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(2104)
                .message("Xóa tin tức thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}