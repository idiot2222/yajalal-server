package me.bogeun.yajalal.repository.account;

import me.bogeun.yajalal.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long>, AccountDynamicRepository {

    Optional<Account> findByUsername(String username);

    Long countByUsername(String username);

    Long countByEmail(String email);
}
