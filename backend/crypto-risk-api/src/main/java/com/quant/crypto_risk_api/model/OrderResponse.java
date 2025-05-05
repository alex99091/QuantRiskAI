package com.quant.crypto_risk_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponse {
    private boolean success;
    private String orderId;
    private String status; // filled, open, canceled
    private String message;
}
