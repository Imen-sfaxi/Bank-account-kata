package org.societegenerale.fr.account.dao;

import org.societegenerale.fr.account.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
