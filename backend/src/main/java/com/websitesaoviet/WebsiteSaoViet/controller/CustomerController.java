package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.common.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CustomerCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.user.CustomerUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.CustomerResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.user.CustomerCreateResponse;
import com.websitesaoviet.WebsiteSaoViet.enums.CustomerStatus;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import com.websitesaoviet.WebsiteSaoViet.service.CustomerService;
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

import java.text.ParseException;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public class CustomerController {
    CustomerService customerService;
    AuthenticationService authenticationService;

    @PostMapping()
    ResponseEntity<ApiResponse<CustomerCreateResponse>> createUser(@RequestBody @Valid CustomerCreationRequest request) {
        ApiResponse<CustomerCreateResponse> apiResponse = ApiResponse.<CustomerCreateResponse>builder()
                .code(1999)
                .message("Thêm khách hàng mới thành công.")
                .result(customerService.createCustomer(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<CustomerResponse>>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("registeredTime")));
        Page<CustomerResponse> usersPage = customerService.getCustomers(pageable);

        ApiResponse<Page<CustomerResponse>> apiResponse = ApiResponse.<Page<CustomerResponse>>builder()
                .code(1998)
                .result(usersPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> getUserById(@PathVariable String id) {
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1997)
                .result(customerService.getCustomerById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/infor")
    ResponseEntity<ApiResponse<CustomerResponse>> getUserByToken(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        String id = authenticationService.getIdByToken(token);

        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1996)
                .result(customerService.getCustomerById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<CustomerResponse>> updateUser(@PathVariable String id, @RequestBody @Valid CustomerUpdateRequest request) {
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .code(1995)
                .message("Cập nhật thông tin khách hàng thành công.")
                .result(customerService.updateCustomer(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{id}")
//    ResponseEntity<ApiResponse<String>> deleteCustomer(@PathVariable String id) {
//        if(orderService.existsByUserId(id)) {
//            throw new AppException(ErrorCode.ORDER_PROCESSING);
//        }
//        customerService.deleteCustomer(id);
//
//        ApiResponse<String> apiResponse = new ApiResponse<>();
//        apiResponse.setCode(1994);
//        apiResponse.setMessage("Xóa khách hàng thành công.");
//
//        return ResponseEntity.ok(apiResponse);
//    }

    @PutMapping("/password/{id}")
    ResponseEntity<ApiResponse<String>> changePassword(@PathVariable String id, @RequestBody @Valid PasswordChangeRequest request) {
        customerService.changePassword(id, request);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1993)
                .message("Thay đổi mật khẩu thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/activate/{id}")
    ResponseEntity<ApiResponse<String>> activateCustomer(@PathVariable String id) {
        customerService.activateCustomer(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1992)
                .message("Kích hoạt thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/block/{id}")
    ResponseEntity<ApiResponse<String>> blockCustomer(@PathVariable String id) {
        customerService.blockCustomer(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1991)
                .message("Chặn tài khoản thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/unblock/{id}")
    ResponseEntity<ApiResponse<String>> unblockCustomer(@PathVariable String id) {
        customerService.unblockCustomer(id);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1990)
                .message("Bỏ chặn tài khoản thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }

//    @PreAuthorize("hasRole('ADMIN')")
//    @GetMapping("/latest")
//    ResponseEntity<ApiResponse<List<LatestUsersResponse>>> getLatestUsers() {
//        ApiResponse<List<LatestUsersResponse>> apiResponse = ApiResponse.<List<LatestUsersResponse>>builder()
//                .code(1993)
//                .result(userService.getLatestUsers())
//                .build();
//
//        return ResponseEntity.ok(apiResponse);
//    }
}