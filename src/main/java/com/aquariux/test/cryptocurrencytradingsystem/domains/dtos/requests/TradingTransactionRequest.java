package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.requests;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.TradingType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TradingTransactionRequest {
    private Symbol symbol;
    private TradingType tradingType;
    private BigDecimal tradingPrice;
    private BigDecimal quantity;
}
