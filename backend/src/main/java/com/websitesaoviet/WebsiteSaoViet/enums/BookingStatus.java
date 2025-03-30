package com.websitesaoviet.WebsiteSaoViet.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum BookingStatus {
    PROCESSING("Đang xử lý"),
    CONFIRM("Đã xác nhận"),
    CANCEL("Đã hủy");

    private final String value;

    BookingStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}