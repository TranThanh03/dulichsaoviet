package com.websitesaoviet.WebsiteSaoViet.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CheckoutStatus {
    PAID("Đã thanh toán"),
    UNPAID("Chưa thanh toán");

    private final String value;

    CheckoutStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}