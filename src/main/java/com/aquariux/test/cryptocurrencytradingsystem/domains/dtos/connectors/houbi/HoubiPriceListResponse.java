package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.connectors.houbi;

import lombok.Data;

import java.util.List;

@Data
public class HoubiPriceListResponse {
    private List<HoubiPriceResponse> data;
}
