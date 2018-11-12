package org.societegenerale.fr.account.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("R")
public class withdrawal extends Operation{

	public withdrawal() {
		super();
	}

	public withdrawal(Date operationDate, double montant, Account account) {
		super(operationDate, montant, account);
	}
}
