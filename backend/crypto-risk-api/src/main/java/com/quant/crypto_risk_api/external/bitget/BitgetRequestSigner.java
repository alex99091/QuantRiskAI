package com.quant.crypto_risk_api.external.bitget;

import com.quant.crypto_risk_api.config.BitgetCredentials;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class BitgetRequestSigner {

    private final BitgetCredentials credentials;

    public String generateSignature(String timestamp, String method, String requestPath, String body) {
        try {
            String preHash = timestamp + method.toUpperCase() + requestPath + (body == null ? "" : body);
            Mac sha256Mac = Mac.getInstance("HmacSHA256");
            SecretKeySpec keySpec = new SecretKeySpec(credentials.getSecret().getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256Mac.init(keySpec);
            byte[] hash = sha256Mac.doFinal(preHash.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate signature", e);
        }
    }
}
