package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.PasswordChangeRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.UserCreationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.request.UserUpdateRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.admin.LatestUsersResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.UserResponse;
import com.websitesaoviet.WebsiteSaoViet.exception.AppException;
import com.websitesaoviet.WebsiteSaoViet.exception.ErrorCode;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import com.websitesaoviet.WebsiteSaoViet.service.OrderService;
import com.websitesaoviet.WebsiteSaoViet.service.PaymentService;
import com.websitesaoviet.WebsiteSaoViet.service.UserService;
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
@RequestMapping("/api/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

@Slf4j
public class UserController {
    UserService userService;
    AuthenticationService authenticationService;
    OrderService orderService;
    PaymentService paymentService;

    @PostMapping()
    ResponseEntity<ApiResponse<UserResponse>> createUser(@RequestBody @Valid UserCreationRequest request) {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1999)
                .message("Thêm khách hàng mới thành công.")
                .result(userService.createUser(request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping()
    ResponseEntity<ApiResponse<Page<UserResponse>>> getUsers(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "6") int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("registerTime")));
        Page<UserResponse> usersPage = userService.getUsers(pageable);

        ApiResponse<Page<UserResponse>> apiResponse = ApiResponse.<Page<UserResponse>>builder()
                .code(1998)
                .result(usersPage)
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> getUserById(@PathVariable String id) {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1997)
                .result(userService.getUserById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/infor")
    ResponseEntity<ApiResponse<UserResponse>> getUserByToken(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        String id = authenticationService.getUserIdByToken(token);

        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1996)
                .result(userService.getUserById(id))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/{id}")
    ResponseEntity<ApiResponse<UserResponse>> updateUser(@PathVariable String id, @RequestBody @Valid UserUpdateRequest request) {
        ApiResponse<UserResponse> apiResponse = ApiResponse.<UserResponse>builder()
                .code(1995)
                .message("Cập nhật thông tin khách hàng thành công.")
                .result(userService.updateUser(id, request))
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String id) {
        if(orderService.existsByUserId(id)) {
            throw new AppException(ErrorCode.ORDER_PROCESSING);
        }
        userService.deleteUser(id);
        paymentService.deleteByUserId(id);
        orderService.deleteByUserId(id);

        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setCode(1994);
        apiResponse.setMessage("Xóa khách hàng thành công.");

        return ResponseEntity.ok(apiResponse);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/latest")
    ResponseEntity<ApiResponse<List<LatestUsersResponse>>> getLatestUsers() {
        ApiResponse<List<LatestUsersResponse>> apiResponse = ApiResponse.<List<LatestUsersResponse>>builder()
                .code(1993)
                .result(userService.getLatestUsers())
                .build();

        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/password/{id}")
    ResponseEntity<ApiResponse<String>> changePassword(@PathVariable String id, @RequestBody @Valid PasswordChangeRequest request) {
        userService.changePassword(id, request);

        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .code(1992)
                .message("Thay đổi mật khẩu thành công.")
                .build();

        return ResponseEntity.ok(apiResponse);
    }
}