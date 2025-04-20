package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HoubiPriceResponse {
    private String symbol;
    @JsonProperty("bid")
    private BigDecimal bidPrice;
    @JsonProperty("ask")
    private BigDecimal askPrice;
}
