package com.quant.crypto_risk_api.external.bitget;

import com.quant.crypto_risk_api.config.BitgetApiProperties;
import com.quant.crypto_risk_api.model.Symbol;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BitgetClientImplTest {

    @Autowired
    BitgetClient bitgetClient;

    @Autowired
    BitgetApiProperties properties;

    @Test
    void getCurrentPrice_shouldReturnValidPrice() {
        double price = bitgetClient.getCurrentPrice(Symbol.BTCUSDT_UMCBL);
        System.out.println("BTC Price: " + price);
        assertThat(price).isGreaterThan(10000);
    }

    @Test
    void getBalance_shouldReturnPositiveValue() {
        double balance = bitgetClient.getBalance("USDT");
        System.out.println("USDT Balance: " + balance);
        assertThat(balance).isGreaterThan(0.0);
    }


}
