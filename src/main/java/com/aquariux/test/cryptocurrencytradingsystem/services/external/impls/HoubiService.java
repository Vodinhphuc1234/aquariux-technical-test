package com.aquariux.test.cryptocurrencytradingsystem.services.external.impls;

import com.aquariux.test.cryptocurrencytradingsystem.configurations.ConfigProperties;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi.HoubiPriceListResponse;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IHoubiService;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IRestCallerService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class HoubiService implements IHoubiService {
    private final IRestCallerService restCallerService;
    private final ConfigProperties configProperties;
    @Override
    public HoubiPriceListResponse getPrices() {
        log.info("Start get prices from houbi");
        return (HoubiPriceListResponse) restCallerService
                .get(configProperties.getHoubiEndpoint(),
                        configProperties.getHoubiPriceApi(),
                        HoubiPriceListResponse.class);
    }
}
