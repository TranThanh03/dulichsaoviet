package com.websitesaoviet.WebsiteSaoViet.controller;

import com.nimbusds.jose.JOSEException;
import com.websitesaoviet.WebsiteSaoViet.dto.request.AuthenticationRequest;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.ApiResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.AuthenticationResponse;
import com.websitesaoviet.WebsiteSaoViet.dto.response.common.IntrospectResponse;
import com.websitesaoviet.WebsiteSaoViet.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationController {
    AuthenticationService authenticationService;

    @PostMapping("/login")
    public ApiResponse<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request, HttpServletResponse response) {
        var result = authenticationService.authenticate(request);
        String jwtToken = result.getToken();

        ResponseCookie cookie = ResponseCookie.from("token", jwtToken)
                .secure(false)
                .sameSite("Strict")
                .path("/")
                .maxAge(60 * 60 * 2)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ApiResponse.<AuthenticationResponse>builder()
                .code(9999)
                .result(AuthenticationResponse.builder()
                        .authenticated(true)
                        .build())
                .build();
    }

    @GetMapping("/introspect")
    public ApiResponse<IntrospectResponse> introspectToken(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);

        var result = authenticationService.introspect(token);

        return ApiResponse.<IntrospectResponse>builder()
                .code(result.isValid() ? 9998 : 4448)
                .result(result)
                .build();
    }

    @GetMapping("/logout")
    ApiResponse<Void> logout(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);
        authenticationService.logout(token);

        return ApiResponse.<Void>builder()
                .code(9997)
                .build();
    }

    @PostAuthorize("returnObject.result.authenticated == true")
    @PostMapping("/admin/login")
    public ApiResponse<AuthenticationResponse> authenticateAdmin(
            @RequestBody AuthenticationRequest request, HttpServletResponse response) {

        var result = authenticationService.authenticate(request);
        String jwtToken = result.getToken();
        var userRoles = result.getRoles();

        boolean isAdmin = userRoles != null && userRoles.contains("ADMIN");

        if (isAdmin) {
            ResponseCookie cookie = ResponseCookie.from("tokenAdmin", jwtToken)
                    .secure(false)
                    .sameSite("Strict")
                    .path("/")
                    .maxAge(60 * 60 * 2)
                    .build();
            response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        }

        return ApiResponse.<AuthenticationResponse>builder()
                .code(isAdmin ? 9996 : 403)
                .result(AuthenticationResponse.builder()
                        .authenticated(isAdmin)
                        .build())
                .build();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/introspect")
    public ApiResponse<IntrospectResponse> introspectTokenAdmin(@RequestHeader("Authorization") String authorizationHeader)
            throws ParseException, JOSEException {
        String token = authenticationService.extractTokenFromHeader(authorizationHeader);

        var result = authenticationService.introspect(token);

        return ApiResponse.<IntrospectResponse>builder()
                .code(result.isValid() ? 9995 : 4447)
                .result(result)
                .build();
    }
}