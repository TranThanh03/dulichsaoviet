package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.CustomerCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.CustomerUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CustomerResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestCustomersResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
import com.websitesaoviet.WebsiteSaoViet.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Slf4j
public class CustomerController {
    CustomerService customerService;
    AuthenticationService authenticationService;
    OrderService orderService;

    @PostMapping()
    ResponseEntity<ApiResponse<CustomerResponse>> createCustomer(@RequestBody @Valid CustomerCreationRequest request) {
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1999)
                .message("Thêm khách hàng mới thành công.")
                .result(customerService.createCustomer(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<CustomerResponse>>> getCustomers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("registerTime")));
        Page<CustomerResponse> usersPage = customerService.getCustomers(pageable);

        ApiResponse<Page<CustomerResponse>> apiResponse = ApiResponse.<Page<CustomerResponse>>builder()
                .code(1998)
                .result(usersPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById(@PathVariable String id) {
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1997)
                .result(customerService.getCustomerById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/infor")
    ResponseEntity<ApiResponse<CustomerResponse>> getCustomerByToken(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        String id = authenticationService.getCustomerIdByToken(token);

        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1996)
                .result(customerService.getCustomerById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> updateCustomer(@PathVariable String id, @RequestBody @Valid CustomerUpdateRequest request) {
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1995)
                .message("Cập nhật thông tin khách hàng thành công.")
                .result(customerService.updateCustomer(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable String id) {
        if(orderService.existsByCustomerId(id)) {
            throw new AppException(ErrorCode.ORDER_PROCESSING);
        }
        customerService.deleteCustomer(id);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1994);
        apiResponse.setMessage("Xóa khách hàng thành công.");

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/latest")
    ResponseEntity<ApiResponse<List<LatestCustomersResponse>>> getLatestCustomers() {
        ApiResponse<List<LatestCustomersResponse>> apiResponse = ApiResponse.<List<LatestCustomersResponse>>builder()
                .code(1993)
                .result(customerService.getLatestCustomers())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/password/{id}")
    ResponseEntity<ApiResponse<String>> changePassword(@PathVariable String id, @RequestBody @Valid PasswordChangeRequest request) {
        customerService.changePassword(id, request);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1992)
                .message("Thay đổi mật khẩu thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}