package com.quant.crypto_risk_api;

import com.quant.crypto_risk_api.config.BitgetApiProperties;
import com.quant.crypto_risk_api.config.BitgetCredentials;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({ BitgetApiProperties.class, BitgetCredentials.class })
public class CryptoRiskApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(CryptoRiskApiApplication.class, args);
	}
}
