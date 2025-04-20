package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseErrorResponse {
    private String code;
    private String message;
    private String ticketId;
    private String attribute;
}
