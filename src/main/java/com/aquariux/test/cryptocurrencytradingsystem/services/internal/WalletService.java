package com.aquariux.test.cryptocurrencytradingsystem.services.internal;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.WalletResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import org.springframework.data.domain.Pageable;

public interface WalletService {
    CustomPage<WalletResponse> getWalletByUserId(Pageable pageable);
}
