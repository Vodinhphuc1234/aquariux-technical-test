package com.aquariux.test.cryptocurrencytradingsystem.domains.entities;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "pending_transaction", uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "transaction_id"}))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PendingTransaction extends AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount user;

    @ManyToOne
    @JoinColumn(name = "transaction_id")
    private TradingTransaction order;
}
