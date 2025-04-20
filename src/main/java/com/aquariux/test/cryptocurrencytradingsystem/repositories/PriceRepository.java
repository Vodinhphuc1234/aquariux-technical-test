package com.aquariux.test.cryptocurrencytradingsystem.repositories;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.Price;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRepository extends JpaRepository<Price, Long> {

    boolean existsBySymbol(Symbol symbol);
    Price findBySymbol(Symbol symbol);
}
