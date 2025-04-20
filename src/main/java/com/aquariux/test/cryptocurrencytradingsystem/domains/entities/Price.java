package com.aquariux.test.cryptocurrencytradingsystem.domains.entities;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "price", uniqueConstraints = @UniqueConstraint(columnNames = "symbol"))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Price extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = false)
    @Enumerated(EnumType.STRING)
    private Symbol symbol;

    @Column(nullable = false, name = "ask_price")
    private BigDecimal askPrice;

    @Column(nullable = false, name = "bid_price")
    private BigDecimal bidPrice;
}
