package org.societegenerale.fr.account.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("V")
public class Deposit extends Operation{

	public Deposit() {
		super();
	}

	public Deposit(Date operationDate, double amount, Account account) {
		super(operationDate, amount, account);
	}
}
