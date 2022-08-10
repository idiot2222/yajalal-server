package me.bogeun.yajalal.repository;

import me.bogeun.yajalal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
