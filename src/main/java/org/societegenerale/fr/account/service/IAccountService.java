package org.societegenerale.fr.account.service;

import org.societegenerale.fr.account.entities.Account;
import org.societegenerale.fr.account.entities.Operation;
import org.springframework.data.domain.Page;

public interface IAccountService {

    Account accountConsultation(String code);
    void makeDeposit(String code, double amount);
    void makeWithdrawal(String code, double amount);
    Page<Operation> operationList(String code, int page, int size);
}
