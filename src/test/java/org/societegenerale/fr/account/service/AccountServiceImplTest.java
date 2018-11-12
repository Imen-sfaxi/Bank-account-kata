package org.societegenerale.fr.account.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.societegenerale.fr.account.entities.Account;
import org.societegenerale.fr.account.entities.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {

    @Autowired
    private AccountServiceImpl accountServiceImpl;

    @Test
    public void accountConsultation_Test() {
        Account account = accountServiceImpl.accountConsultation("c1");
        assertThat(account.getAmount()).isEqualTo(10000);
    }

    @Test(expected = RuntimeException.class)
    public void accountConsultation_Exception_Test() {
        when(accountServiceImpl.accountConsultation("c6")).thenThrow(new RuntimeException());
    }

    @Test
    public void makeDeposit_Test() {
        accountServiceImpl.makeDeposit("c1", 1000);
        assertThat(accountServiceImpl.accountConsultation("c1").getAmount()).isEqualTo(10000);
    }

    @Test
    public void makeWithdrawal_Test() {
        accountServiceImpl.makeWithdrawal("c1", 3000);
        assertThat(accountServiceImpl.accountConsultation("c1").getAmount()).isEqualTo(7000);
    }

    @Test
    public void listOperation_Test() {
        Page<Operation> operations = accountServiceImpl.operationList("c1", 0, 5);
        assertThat(operations.getSize()).isEqualTo(5);
        Operation operation = operations.getContent().get(0);
        assertThat(operation.getAmount()).isEqualTo(3000);
        assertThat(operation.getAccount().getCode()).isEqualTo("c1");
    }
}