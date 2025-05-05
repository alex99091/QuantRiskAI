package com.quant.crypto_risk_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bitget")
public class BitgetApiProperties {
    private String baseUrl;
    private String tickerPath;
}
