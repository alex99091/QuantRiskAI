package com.quant.crypto_risk_api.model;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path
) {}

