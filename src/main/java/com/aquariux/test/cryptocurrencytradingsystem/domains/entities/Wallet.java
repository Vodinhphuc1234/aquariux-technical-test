package com.aquariux.test.cryptocurrencytradingsystem.domains.entities;

import com.aquariux.test.cryptocurrencytradingsystem.commons.enums.Symbol;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "wallet", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "currency"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Wallet extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    private Symbol symbol;

    @Column(nullable = false)
    private BigDecimal quantity;
}
