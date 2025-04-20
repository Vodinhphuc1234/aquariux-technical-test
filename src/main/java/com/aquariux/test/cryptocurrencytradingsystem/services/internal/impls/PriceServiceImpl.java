package com.aquariux.test.cryptocurrencytradingsystem.services.internal.impls;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import com.aquariux.test.cryptocurrencytradingsystem.commons.utils.ConverterUtils;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.AggregatedPriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.binance.BinancePriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi.HoubiPriceListResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi.HoubiPriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.PriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Price;
import com.aquariux.test.cryptocurrencytradingsystem.mappers.PriceMapper;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.PriceRepository;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IBinanceService;
import com.aquariux.test.cryptocurrencytradingsystem.services.external.IHoubiService;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class PriceServiceImpl implements PriceService {
    private final IBinanceService binanceService;
    private final IHoubiService houbiService;

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    @Transactional
    public void syncPrice() {
        Map<String, AggregatedPriceResponse> bestMarketPrices = getBestPrices();
        for (AggregatedPriceResponse priceResponse : bestMarketPrices.values()){
            Symbol symbol = Symbol.valueOf(priceResponse.getSymbol());
            if (!priceRepository.existsBySymbol(symbol)) {
                priceRepository.save(Price.builder()
                        .askPrice(priceResponse.getAskPrice())
                        .bidPrice(priceResponse.getBidPrice())
                        .symbol(symbol).build());
                continue;
            }
            Price price = priceRepository.findBySymbol(symbol);
            price.setAskPrice(priceResponse.getAskPrice());
            price.setBidPrice(priceResponse.getBidPrice());
            priceRepository.save(price);
        }
    }

    @Override
    public CustomPage<PriceResponse> getAvailablePrices(Pageable pageable) {
        Page<Price> prices = priceRepository.findAll(pageable);
        return priceMapper.toDTOs(prices);
    }

    private Map<String, AggregatedPriceResponse> getBestPrices() {
        List<BinancePriceResponse> binancePriceResponses = binanceService.getPrices();
        HoubiPriceListResponse houbiPriceListResponse = houbiService.getPrices();

        Map<String, AggregatedPriceResponse> responseMap = new HashMap<>();
        if (binancePriceResponses != null) {
            for (BinancePriceResponse binancePriceResponse : binancePriceResponses) {
                String symbol = binancePriceResponse.getSymbol().toUpperCase();
                if (!Symbol.isSupported(symbol)) {
                    continue;
                }
                if (!responseMap.containsKey(symbol)) {
                    responseMap.put(symbol,
                            ConverterUtils.convertObjectToObject(binancePriceResponse, AggregatedPriceResponse.class));
                    continue;
                }
                AggregatedPriceResponse priceResponse = responseMap.get(symbol);
                // highest bid
                if (binancePriceResponse.getBidPrice().compareTo(priceResponse.getBidPrice()) > 0) {
                    priceResponse.setBidPrice(binancePriceResponse.getBidPrice());
                }
                // lowest ask
                if (binancePriceResponse.getAskPrice().compareTo(priceResponse.getAskPrice()) < 0) {
                    priceResponse.setAskPrice(binancePriceResponse.getAskPrice());
                }
                responseMap.put(symbol, priceResponse);
            }
        }

        if (houbiPriceListResponse != null && houbiPriceListResponse.getData() != null) {
            for (HoubiPriceResponse houbiPriceResponse : houbiPriceListResponse.getData()) {
                String symbol = houbiPriceResponse.getSymbol().toUpperCase();
                if (!Symbol.isSupported(symbol)) {
                    continue;
                }
                if (!responseMap.containsKey(symbol)) {
                    responseMap.put(symbol,
                            ConverterUtils.convertObjectToObject(houbiPriceResponse, AggregatedPriceResponse.class));
                    continue;
                }
                AggregatedPriceResponse priceResponse = responseMap.get(symbol);
                // highest bid
                if (houbiPriceResponse.getBidPrice().compareTo(priceResponse.getBidPrice()) > 0) {
                    priceResponse.setBidPrice(houbiPriceResponse.getBidPrice());
                }
                // lowest ask
                if (houbiPriceResponse.getAskPrice().compareTo(priceResponse.getAskPrice()) < 0) {
                    priceResponse.setAskPrice(houbiPriceResponse.getAskPrice());
                }
                responseMap.put(symbol, priceResponse);
            }
        }

        return responseMap;
    }
}