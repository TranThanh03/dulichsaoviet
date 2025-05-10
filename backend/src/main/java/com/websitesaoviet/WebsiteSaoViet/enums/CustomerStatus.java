package com.websitesaoviet.WebsiteSaoViet.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CustomerStatus {
    ACTIVATE("Đang hoạt động"),
    INACTIVATE("Chưa kích hoạt"),
    BLOCKED("Bị khóa");

    private final String value;

    CustomerStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}