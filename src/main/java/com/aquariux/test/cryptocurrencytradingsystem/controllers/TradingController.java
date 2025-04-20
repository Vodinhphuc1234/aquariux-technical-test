package com.aquariux.test.cryptocurrencytradingsystem.controllers;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.requests.TradingTransactionRequest;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseResponse;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.TradingTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tradings")
@RequiredArgsConstructor
public class TradingController {

    private final TradingTransactionService tradingTransactionService;

    @PostMapping("")
    public BaseResponse<Object> trade(@RequestBody TradingTransactionRequest request) {
        tradingTransactionService.doTrading(request);
        return BaseResponse
                .builder()
                .code(ErrorConstants.ERROR_CODE.SUCCESS)
                .message(ErrorConstants.ERROR_MESSAGE.SUCCESS)
                .build();
    }
}
