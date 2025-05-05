package com.quant.crypto_risk_api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PositionInfo {
    private Symbol symbol;
    private Direction direction;
    private double entryPrice;
    private double size;
    private double pnl;
    private double leverage;
}
