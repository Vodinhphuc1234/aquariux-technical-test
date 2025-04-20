package com.aquariux.test.cryptocurrencytradingsystem.configurations;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ConfigProperties {
    @Value("${rest.api.binance.endpoint}")
    private String binanceEndpoint;
    @Value("${rest.api.binance.path.price}")
    private String binancePriceApi;
    @Value("${rest.api.houbi.endpoint}")
    private String houbiEndpoint;
    @Value("${rest.api.houbi.path.price}")
    private String houbiPriceApi;
}
