package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponseList {
    private List<BaseErrorResponse> errors;
}
