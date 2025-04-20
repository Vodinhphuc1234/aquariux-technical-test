package com.aquariux.test.cryptocurrencytradingsystem.controllers;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.TradingTransactionResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.WalletResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.TradingTransactionService;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/me")
@RequiredArgsConstructor
public class MeController {

    private final WalletService walletService;
    private final TradingTransactionService tradingTransactionService;

    @GetMapping("/wallets")
    public BaseResponse<CustomPage<WalletResponse>> getPrice(@RequestParam(required = false) int limit,
                                                             @RequestParam(defaultValue = "0") int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        CustomPage<WalletResponse> walletResponseCustomPage = walletService.getWalletByUserId(pageable);
        return BaseResponse
                .<CustomPage<WalletResponse>>builder()
                .code(ErrorConstants.ERROR_CODE.SUCCESS)
                .message(ErrorConstants.ERROR_MESSAGE.SUCCESS)
                .data(walletResponseCustomPage)
                .build();
    }

    @GetMapping("/trading-histories")
    public BaseResponse<CustomPage<TradingTransactionResponse>> getTradingHistories(@RequestParam(required = false) int limit,
                                                                                    @RequestParam(defaultValue = "0") int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        CustomPage<TradingTransactionResponse> orderBookResponseCustomPage = tradingTransactionService.getTradingTransactionByUserId(pageable);
        return BaseResponse
                .<CustomPage<TradingTransactionResponse>>builder()
                .code(ErrorConstants.ERROR_CODE.SUCCESS)
                .message(ErrorConstants.ERROR_MESSAGE.SUCCESS)
                .data(orderBookResponseCustomPage)
                .build();
    }
}
