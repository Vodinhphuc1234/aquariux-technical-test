package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AggregatedPriceResponse {
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
}
