package com.aquariux.test.cryptocurrencytradingsystem.services.internal.impls;

import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.WalletResponse;
import com.aquariux.test.cryptocurrencytradingsystem.domains.dtos.responses.base.CustomPage;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Wallet;
import com.aquariux.test.cryptocurrencytradingsystem.mappers.WalletMapper;
import com.aquariux.test.cryptocurrencytradingsystem.repositories.WalletRepository;
import com.aquariux.test.cryptocurrencytradingsystem.services.internal.WalletService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final WalletMapper walletMapper;

    @Override
    public CustomPage<WalletResponse> getWalletByUserId(Pageable pageable) {
        String userIdStr = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Page<Wallet> walletPage = walletRepository.findByUserId(Long.parseLong(userIdStr), pageable);
        return walletMapper.toDTOs(walletPage);
    }
}