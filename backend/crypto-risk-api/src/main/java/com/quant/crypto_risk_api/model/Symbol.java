package com.quant.crypto_risk_api.model;

public enum Symbol {
    BTCUSDT_UMCBL("BTCUSDT_UMCBL"),
    ETHUSDT_UMCBL("ETHUSDT_UMCBL");

    private final String value;

    Symbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
