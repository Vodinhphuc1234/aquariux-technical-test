package com.aquariux.test.cryptocurrencytradingsystem.services.external;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.binance.BinancePriceResponse;

import java.util.List;

public interface IBinanceService {
    List<BinancePriceResponse> getPrices();
}
