package com.quant.crypto_risk_api.service.impl;

import com.quant.crypto_risk_api.external.bitget.BitgetClient;
import com.quant.crypto_risk_api.model.Symbol;
import com.quant.crypto_risk_api.service.PriceFetcherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PriceFetcherServiceImpl implements PriceFetcherService {

    private final BitgetClient bitgetClient;

    @Override
    public double getPrice(Symbol symbol) {
        return bitgetClient.getCurrentPrice(symbol);
    }
}
