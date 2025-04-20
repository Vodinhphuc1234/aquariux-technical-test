package com.aquariux.test.cryptocurrencytradingsystem.services.internal;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.PriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import org.springframework.data.domain.Pageable;

public interface PriceService {
    void syncPrice();

    CustomPage<PriceResponse> getAvailablePrices(Pageable pageable);
}
