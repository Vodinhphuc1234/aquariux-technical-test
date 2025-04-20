package com.aquariux.test.cryptocurrencytradingsystem.services.internal;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.requests.TradingTransactionRequest;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.TradingTransactionResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import org.springframework.data.domain.Pageable;

public interface TradingTransactionService {
    CustomPage<TradingTransactionResponse> getTradingTransactionByUserId(Pageable pageable);
    void doTrading (TradingTransactionRequest tradingTransactionRequest);
}
