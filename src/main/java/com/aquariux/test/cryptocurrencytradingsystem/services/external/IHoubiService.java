package com.aquariux.test.cryptocurrencytradingsystem.services.external;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi.HoubiPriceListResponse;

public interface IHoubiService {
    HoubiPriceListResponse getPrices();
}
