package com.quant.crypto_risk_api.service;

import com.quant.crypto_risk_api.model.Symbol;

public interface PriceFetcherService {
    double getPrice(Symbol symbol);
}
