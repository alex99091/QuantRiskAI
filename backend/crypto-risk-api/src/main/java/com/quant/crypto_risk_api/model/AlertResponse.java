package com.quant.crypto_risk_api.model;

import java.time.LocalDateTime;

public record AlertResponse(
        String strategy,
        String symbol,
        String direction,
        String message,
        LocalDateTime triggeredAt
) {}

/* 예상응답: JSON
{
  "strategy": "RSI-BB",
  "symbol": "ETHUSDT",
  "direction": "SHORT",
  "message": "RSI 75 + BB 상단 돌파",
  "triggeredAt": "2025-05-04T21:38:00"
}
 */
