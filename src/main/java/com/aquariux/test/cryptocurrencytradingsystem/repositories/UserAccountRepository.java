package com.aquariux.test.cryptocurrencytradingsystem.repositories;

import com.aquariux.test.cryptocurrencytradingsystem.domains.entities.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {
}
