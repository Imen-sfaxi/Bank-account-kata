package org.societegenerale.fr.account.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CE")
public class AccountEpargne extends Account {

	
	private double rate;

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public AccountEpargne() {
		super();
	}

	public AccountEpargne(String code, Date creationDate, double amount, Client client, double rate) {
		super(code, creationDate, amount, client);
		this.rate = rate;
	}
	
	
}
