package com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WalletResponse {
    private Long id;
    private Symbol symbol;
    private BigDecimal quantity;
    private Instant createdDate;
    private Instant lastModifiedDate;
}
