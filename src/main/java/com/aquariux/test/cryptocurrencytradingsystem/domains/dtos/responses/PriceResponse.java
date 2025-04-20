package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PriceResponse {
    private Long id;
    private String symbol;
    private BigDecimal bidPrice;
    private BigDecimal askPrice;
    private Instant lastModifiedDate;
}
