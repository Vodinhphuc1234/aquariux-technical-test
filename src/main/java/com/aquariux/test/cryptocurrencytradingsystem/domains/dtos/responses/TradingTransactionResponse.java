package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.TradingType;
import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TradingTransactionResponse {
    private Long id;
    private Symbol symbol;
    private TradingType tradingType;
    private BigDecimal tradingPrice;
    private BigDecimal quantity;
    private Boolean isActive;
}
