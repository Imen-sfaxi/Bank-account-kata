package org.societegenerale.fr.account.service;

import org.societegenerale.fr.account.dao.AccountRepository;
import org.societegenerale.fr.account.dao.OperationRepository;
import org.societegenerale.fr.account.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;

    private final OperationRepository operationRepository;

    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
    }

    @Override
    public Account accountConsultation(String code) {
       Optional<Account> account = accountRepository.findById(code);
       if(!account.isPresent()) throw new RuntimeException("Account not found");
        return account.get();
    }

    @Override
    public void makeDeposit(String code, double montant) {
        Account account = accountConsultation(code);
        Deposit deposit = new Deposit(new Date(), montant, account);
        operationRepository.save(deposit);
        account.setAmount(account.getAmount() + montant);
        accountRepository.save(account);

    }

    @Override
    public void makeWithdrawal(String code, double amount) {
        Account account = accountConsultation(code);
        double discovered = 0;
        if(account instanceof CurrentAccount)
            discovered = ((CurrentAccount) account).getDiscovered();
        if(discovered + account.getAmount()<amount)
            throw new RuntimeException("Insufficient amount");
        withdrawal withdrawal = new withdrawal(new Date(), amount, account);
        operationRepository.save(withdrawal);
        account.setAmount(account.getAmount() - amount);
        accountRepository.save(account);
    }

    @Override
    public Page<Operation> operationList(String codeCpte, int page, int size) {
        return operationRepository.operationList(codeCpte, PageRequest.of(page,size));
    }
}
