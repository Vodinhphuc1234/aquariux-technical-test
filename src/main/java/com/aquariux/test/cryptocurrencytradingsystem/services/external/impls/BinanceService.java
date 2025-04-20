package com.aquariux.test.cryptocurrencytradingsystem.services.external.impls;

import com.aquariux.test.cryptocurrencytradingsystem.commons.utils.ConverterUtils;
import com.aquariux.test.cryptocurrencytradingsystem.configurations.ConfigProperties;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.binance.BinancePriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IBinanceService;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IRestCallerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BinanceService implements IBinanceService {
    private final IRestCallerService restCallerService;
    private final ConfigProperties configProperties;
    @Override
    public List<BinancePriceResponse> getPrices() {
        log.info("Start get prices from binance");
        List<?> list = (List<?>) restCallerService.get(configProperties.getBinanceEndpoint(),
                        configProperties.getBinancePriceApi(),
                        List.class);
        return list.stream().map(object -> ConverterUtils.convertObjectToObject(object, BinancePriceResponse.class)).toList();
    }
}
