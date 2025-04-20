package com.aquariux.test.cryptocurrencytradingsystem.jobs;

import com.aquariux.test.cryptocurrencytradingsystem.services.internal.PriceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class FetchPriceJob {
    private final PriceService priceService;
    @Scheduled(cron = "${job.fetch-price.cron}")
    public void scanAndReconcileBillInfoData() {
        try {
            log.info("Start sync price");
            priceService.syncPrice();
            log.info("End sync price");
        } catch (Exception e) {
            log.error("Failed to sync price", e);
        }
    }
}
