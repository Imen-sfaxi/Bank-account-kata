package org.societegenerale.fr.account;

import org.societegenerale.fr.account.dao.ClientRepository;
import org.societegenerale.fr.account.dao.AccountRepository;
import org.societegenerale.fr.account.dao.OperationRepository;
import org.societegenerale.fr.account.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class AccountApplication implements CommandLineRunner {

    private final ClientRepository clientRepository;

    private final AccountRepository accountRepository;

    private final OperationRepository operationRepository;

    @Autowired
    public AccountApplication(ClientRepository clientRepository, AccountRepository accountRepository, OperationRepository operationRepository) {
        this.clientRepository = clientRepository;
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Client c1 = clientRepository.save(new Client("Alice", "alice.rio@yahoo.fr"));
        Client c2 = clientRepository.save(new Client("Jean", "jean.maquignon.gmail.com"));

        Account account_1 = accountRepository.save(new CurrentAccount("c1", new Date(), 9000.0, c1, 300.0));
        Account account_2 = accountRepository.save(new AccountEpargne("c2", new Date(), 6000.0, c2, 50.0));

        operationRepository.save(new Deposit(new Date(), 9000.0, account_1));
        operationRepository.save(new Deposit(new Date(), 1000.0, account_1));
        operationRepository.save(new withdrawal(new Date(), 2000.0, account_1));
        operationRepository.save(new Deposit(new Date(), 4000.0, account_1));

        operationRepository.save(new Deposit(new Date(), 2000.0, account_2));
        operationRepository.save(new Deposit(new Date(), 3000.0, account_2));
        operationRepository.save(new withdrawal(new Date(), 4000.0, account_2));
        operationRepository.save(new Deposit(new Date(), 1000.0, account_2));
    }
}
