package com.quant.crypto_risk_api.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "bitget.auth")
public class BitgetCredentials {
    private String apiKey;
    private String secret;
    private String passphrase;
}
