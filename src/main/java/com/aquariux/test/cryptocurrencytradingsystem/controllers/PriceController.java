package com.aquariux.test.cryptocurrencytradingsystem.controllers;

import com.aquariux.test.cryptocurrencytradingsystem.commons.constants.ErrorConstants;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.PriceResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.BaseResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.PriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/prices")
@RequiredArgsConstructor
public class PriceController {

    private final PriceService priceService;

    @GetMapping("")
    public BaseResponse<CustomPage<PriceResponse>> getPrice(@RequestParam(required = false) int limit,
                                                            @RequestParam(defaultValue = "0") int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        CustomPage<PriceResponse> priceResponses = priceService.getAvailablePrices(pageable);
        return BaseResponse
                .<CustomPage<PriceResponse>>builder()
                .code(ErrorConstants.ERROR_CODE.SUCCESS)
                .message(ErrorConstants.ERROR_MESSAGE.SUCCESS)
                .data(priceResponses)
                .build();
    }
}
