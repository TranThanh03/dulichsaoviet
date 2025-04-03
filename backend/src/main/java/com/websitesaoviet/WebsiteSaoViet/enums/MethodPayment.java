package com.websitesaoviet.WebsiteSaoViet.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum MethodPayment {
    MOMO("MoMo"),
    VNPAY("VNPay"),
    CASH("Tiền mặt");

    private final String value;

    MethodPayment(String value) {
        this.value = value;
    }

    @JsonValue
    public String getValue() {
        return value;
    }
}