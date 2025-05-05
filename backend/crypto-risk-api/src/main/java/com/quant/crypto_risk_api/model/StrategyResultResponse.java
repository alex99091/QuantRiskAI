package com.quant.crypto_risk_api.model;

public record StrategyResultResponse(
        String symbol,
        double sharpeRatio,
        double winRate,
        double maxDrawdown,
        double annualReturn
) {}

/* 예상응답: JSON
{
  "symbol": "BTCUSDT",
  "sharpeRatio": 1.43,
  "winRate": 0.66,
  "maxDrawdown": -12.5,
  "annualReturn": 34.2
}
 */