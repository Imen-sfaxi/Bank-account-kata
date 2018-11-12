package org.societegenerale.fr.account.entities;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.Date;

@Entity
@DiscriminatorValue("CC")
public class CurrentAccount extends Account {

	private double discovered;

	public double getDiscovered() {
		return discovered;
	}

	public void setDiscovered(double discovered) {
		this.discovered = discovered;
	}

	public CurrentAccount() {
		super();
	}

	public CurrentAccount(String code, Date creationDate, double amount, Client client, double discovered) {
		super(code, creationDate, amount, client);
		this.discovered = discovered;
	}
	
	
	
}
