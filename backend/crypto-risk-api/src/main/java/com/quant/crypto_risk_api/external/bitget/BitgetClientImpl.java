package com.quant.crypto_risk_api.external.bitget;

import com.quant.crypto_risk_api.config.BitgetApiProperties;
import com.quant.crypto_risk_api.config.BitgetCredentials;
import com.quant.crypto_risk_api.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Slf4j
@Primary
@Component
@RequiredArgsConstructor
public class BitgetClientImpl implements BitgetClient {

    private final BitgetApiProperties properties;
    private final BitgetRequestSigner signer;
    private final BitgetCredentials credentials;
    private final WebClient webClient = WebClient.builder()
            .baseUrl("https://api.bitget.com") // 필요 시 properties에서 읽도록 개선 가능
            .build();

    @Override
    public double getCurrentPrice(Symbol symbol) {
        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/mix/v1/market/ticker")
                        .queryParam("symbol", symbol.getValue())
                        .queryParam("productType", "umcbl")
                        .build())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        Map<String, Object> data = (Map<String, Object>) response.get("data");
        return Double.parseDouble((String) data.get("last"));
    }

    @Override
    public double getBalance(String coin) {
        String path = "/api/mix/v1/account/accounts";
        String method = "GET";
        String query = "productType=umcbl";
        String timestamp = Instant.now().toString();

        String fullPathWithQuery = path + "?" + query;
        String signature = signer.generateSignature(timestamp, method, path, "");

        Map<String, Object> response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path(path)
                        .queryParam("productType", "umcbl")
                        .build())
                .header("ACCESS-KEY", credentials.getApiKey())
                .header("ACCESS-SIGN", signature)
                .header("ACCESS-TIMESTAMP", timestamp)
                .header("ACCESS-PASSPHRASE", credentials.getPassphrase())
                .retrieve()
                .bodyToMono(Map.class)
                .block();

        List<Map<String, Object>> dataList = (List<Map<String, Object>>) response.get("data");
        for (Map<String, Object> asset : dataList) {
            if (coin.equalsIgnoreCase((String) asset.get("marginCoin"))) {
                return Double.parseDouble((String) asset.get("available"));
            }
        }
        return 0.0;
    }


    @Override
    public PositionInfo getOpenPosition(Symbol symbol) {
        // TODO: Bitget 포지션 조회 API 연동
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OrderResponse enterPosition(Symbol symbol, int leverage, double amount, Direction direction) {
        // TODO: Bitget 포지션 진입 API 연동 (POST /api/mix/v1/order/placeOrder)
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OrderResponse closePosition(Symbol symbol) {
        // TODO: Bitget 포지션 청산 API 연동
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public OrderResponse cancelOrder(String orderId) {
        // TODO: Bitget 주문 취소 API 연동
        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    public List<OrderResponse> getOpenOrders(Symbol symbol) {
        // TODO: Bitget 오픈 주문 조회 API 연동
        return Collections.emptyList();
    }
}
