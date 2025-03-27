package com.websitesaoviet.WebsiteSaoViet.exception;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(4444, "Uncategorized error!", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHENTICATED(4445, "Unauthenticated!", HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(4446, "Unauthorized!", HttpStatus.FORBIDDEN),
    TOKEN_INVALID(4447, "Token invalid!", HttpStatus.OK),
    PHONENUMBER_EXISTED(1001, "Số điện thoại đã tồn tại!", HttpStatus.OK),
    EMAIL_EXISTED(1002, "Email đã tồn tại!", HttpStatus.OK),
    PASSWORD_INVALID(1003, "Mật khẩu có độ dài từ 8 ký tự trở lên!", HttpStatus.OK),
    PHONENUMBER_INVALID(1004, "Số điện thoại phải có đúng 10 chữ số!", HttpStatus.OK),
    EMAIL_INVALID(1005, "Email phải đúng định dạng 'example@example.com'!", HttpStatus.OK),
    FULLNAME_INVALID (1006,"Họ tên không chứa số hoặc ký tự đặc biệt!", HttpStatus.OK),
    FULLNAME_LENGTH_INVALID(1007, "Họ tên có độ dài trong khoảng 5 đến 50 ký tự!", HttpStatus.OK),
    NOT_NULL_LOGIN(1008, "Vui lòng nhập thông tin để đăng nhập!", HttpStatus.OK),
    USER_NOT_EXITED(1009, "Khách hàng không tồn tại!", HttpStatus.OK),
    LOGIN_FAILED(1010, "Tài khoản hoặc mật khẩu không đúng!", HttpStatus.OK),
    TOUR_NOT_EXITED(1011, "Tour không tồn tại!", HttpStatus.OK),
    PEOPLE_INVALID(1012, "Số lượng người phải lớn hơn 0!", HttpStatus.OK),
    ASSIGNMENT_NOT_EXITED(1013, "Lịch phân công không tồn tại!", HttpStatus.OK),
    GUIDE_ASSIGNED(1014, "Hướng dẫn viên này bị trùng lịch phân công!", HttpStatus.OK),
    STARTDATE_NOT_NULL(1015, "Ngày khởi hành không được bỏ trống!", HttpStatus.OK),
    ENDDATE_NOT_NULL(1016, "Ngày kết thúc không được bỏ trống!", HttpStatus.OK),
    STARTDATE_INVALID(1017, "Ngày khởi hành không được trước ngày " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "!", HttpStatus.OK),
    ENDDATE_INVALID(1018, "Ngày kết thúc không được trước ngày khởi hành!", HttpStatus.OK),
    GUIDE_NOT_EXITED(1019, "Hướng dẫn viên không tồn tại!", HttpStatus.OK),
    ORDER_NOT_EXITED(1020, "Lịch đặt không tồn tại!", HttpStatus.OK),
    PAYMENT_NOT_EXITED(1021, "Hóa đơn không tồn tại!", HttpStatus.OK),
    ASSIGNMENT_PEOPLE_INVALID(1022, "Số lượng người đặt vượt quá số lượng người tối đa!" , HttpStatus.OK),
    PAYMENT_MOMO_FALIED(1023, "Không thể thanh toán bằng Momo!", HttpStatus.OK),
    RESULT_MOMO_FALIED(1024, "Thanh toán qua MoMo thất bại!", HttpStatus.OK),
    INVALID_ORDER(1025, "Lịch đặt không hợp lệ!", HttpStatus.OK),
    ORDER_PROCESSING(1026, "Tồn tại lịch đặt đang xử lý!", HttpStatus.OK),
    GUIDE_PRICE_INVALID(1027, "Giá hướng dẫn viên phải lớn hơn 0!", HttpStatus.OK),
    INVALID_PASSWORD(1028, "Mật khẩu không hợp lệ!", HttpStatus.OK),
    DATE_INVALID(1029, "Thời gian không hợp lệ!" , HttpStatus.OK),
    ;
    
    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatusCode getStatusCode() {
        return statusCode;
    }
}