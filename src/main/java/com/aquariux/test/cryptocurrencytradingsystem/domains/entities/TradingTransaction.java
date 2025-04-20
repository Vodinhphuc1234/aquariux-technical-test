package com.aquariux.test.cryptocurrencytradingsystem.domains.entities;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.TradingType;
import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "trading_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TradingTransaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Symbol symbol;

    @Column(name = "trading_type", length = 4)
    @Enumerated(EnumType.STRING)
    private TradingType tradingType;

    @Column(name = "trading_price")
    private BigDecimal tradingPrice;

    @Column(nullable = false)
    private BigDecimal quantity;

    @Column(name = "is_active")
    private Boolean isActive = true;
}
