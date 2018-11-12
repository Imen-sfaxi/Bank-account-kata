package org.societegenerale.fr.account.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;


@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_CPTE",discriminatorType=DiscriminatorType.STRING,length=2 )
public abstract class Account implements Serializable{

	@Id
	private String code;
	private Date creationDate;
	private double amount;
	
	@ManyToOne
	@JoinColumn(name="CODE_CLI")
	private Client client;
	
	@OneToMany(mappedBy="account")
	private Collection<Operation> operations;

	public Account(String code, Date creationDate, double amount, Client client) {
		super();
		this.code = code;
		this.creationDate = creationDate;
		this.amount = amount;
		this.client = client;
	}

	public Account() {
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}



	
	
}
