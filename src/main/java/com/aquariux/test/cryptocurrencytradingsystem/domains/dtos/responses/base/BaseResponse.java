package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse<T> {
    private String code;
    private String message;
    private T data;
}
