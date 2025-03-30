package com.websitesaoviet.WebsiteSaoViet.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum CommonStatus {
    NOT_STARTED("Chưa diễn ra"),
    IN_PROGRESS("Đang diễn ra"),
    COMPLETED("Đã kết thúc");

    private final String value;

    CommonStatus(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}