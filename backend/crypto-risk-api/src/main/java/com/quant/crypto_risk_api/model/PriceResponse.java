package com.quant.crypto_risk_api.model;

import java.time.LocalDateTime;

public record PriceResponse(
        String symbol,
        double price,
        LocalDateTime timestamp
) {}

/* 예상응답: JSON
{
  "symbol": "BTCUSDT",
  "price": 63250.12,
  "timestamp": "2025-05-04T21:40:00"
}
 */