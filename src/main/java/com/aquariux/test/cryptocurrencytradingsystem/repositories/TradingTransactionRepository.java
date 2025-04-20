package com.aquariux.test.cryptocurrencytradingsystem.repositories;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.TradingType;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.TradingTransaction;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.UserAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface TradingTransactionRepository extends JpaRepository<TradingTransaction, Long> {
    Page<TradingTransaction> findByUserId(Long userId, Pageable pageable);

    List<TradingTransaction> findTradingTransactionsByTradingTypeAndSymbolAndQuantityIsGreaterThanAndTradingPriceIsLessThanEqualAndUserIsNot
            (TradingType transactionType, Symbol symbol, BigDecimal quantity, BigDecimal transactionPrice, UserAccount userAccount);

    List<TradingTransaction> findTradingTransactionsByTradingTypeAndSymbolAndQuantityIsGreaterThanAndTradingPriceIsGreaterThanEqualAndUserIsNot
            (TradingType transactionType, Symbol symbol, BigDecimal quantity, BigDecimal transactionPrice, UserAccount userAccount);
}
