package com.quant.crypto_risk_api.external.bitget;

import com.quant.crypto_risk_api.model.*;

import java.util.List;

public interface BitgetClient {

    double getCurrentPrice(Symbol symbol);

    double getBalance(String coin);  // 예: "USDT"

    PositionInfo getOpenPosition(Symbol symbol);

    OrderResponse enterPosition(Symbol symbol, int leverage, double amount, Direction direction);

    OrderResponse closePosition(Symbol symbol);

    OrderResponse cancelOrder(String orderId);

    List<OrderResponse> getOpenOrders(Symbol symbol);
}
