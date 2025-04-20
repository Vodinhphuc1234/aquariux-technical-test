package com.aquariux.test.cryptocurrencytradingsystem.repositories;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Wallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    Page<Wallet> findByUserId(Long userId, Pageable pageable);

    Wallet findByUserIdAndSymbol(Long userId, Symbol symbol);
}
