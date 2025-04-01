package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.common.AuthenticationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.time.Duration;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<String> authenticate(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response) {

        String jwtToken = authenticationService.authenticate(request);

        ResponseCookie cookie = ResponseCookie.from("token", jwtToken)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiResponse.<String>builder()
                .code(9999)
                .message("Đăng nhập thành công.")
                .build();
    }

    @GetMapping("/introspect")
    public ApiResponse<Boolean> introspectToken(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        var result = authenticationService.introspect(token);

        return ApiResponse.<Boolean>builder()
                .code(result ? 9998 : 4448)
                .result(result)
                .build();
    }

    @PostMapping("/logout")
    ApiResponse<String> logout(
            @RequestHeader("Authorization") String authorizationHeader,
            HttpServletResponse response)
            throws ParseException, JOSEException {

        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        authenticationService.logout(token);

        ResponseCookie cookie = ResponseCookie.from("token", "")
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ZERO)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiResponse.<String>builder()
                .code(9997)
                .message("Đăng xuất thành công.")
                .build();
    }

    @PostMapping("/admin/login")
    public ApiResponse<String> authenticateAdmin(
            @RequestBody AuthenticationRequest request,
            HttpServletResponse response) {

        String jwtToken = authenticationService.authenticateAdmin(request);

        ResponseCookie cookie = ResponseCookie.from("tokenAdmin", jwtToken)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(60 * 60)
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiResponse.<String>builder()
                .code(9996)
                .message("Đăng nhập thành công.")
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/introspect")
    public ApiResponse<Boolean> introspectTokenAdmin(@RequestHeader("Authorization") String authorizationHeader) {

        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        var result = authenticationService.introspect(token);

        return ApiResponse.<Boolean>builder()
                .code(result ? 9995 : 4447)
                .result(result)
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/logout")
    ApiResponse<String> logoutAdmin(
            @RequestHeader("Authorization") String authorizationHeader,
            HttpServletResponse response)
            throws ParseException, JOSEException {

        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        authenticationService.logout(token);

        ResponseCookie cookie = ResponseCookie.from("tokenAdmin", "")
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(Duration.ZERO)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiResponse.<String>builder()
                .code(9994)
                .message("Đăng xuất thành công.")
                .build();
    }
}