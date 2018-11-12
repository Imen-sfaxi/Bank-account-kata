package org.societegenerale.fr.account.dao;

import org.societegenerale.fr.account.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, String> {
}
