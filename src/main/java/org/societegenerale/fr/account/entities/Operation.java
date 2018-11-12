package org.societegenerale.fr.account.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING,length=1)
public abstract class Operation implements Serializable{
@Id
@GeneratedValue
private Long number;
private Date operationDate;
private double amount
        ;
@ManyToOne
@JoinColumn(name="CODE_CPTE")
private Account account;


public Operation() {
}

public Operation(Date operationDate, double amount, Account account) {
	super();
	
	this.operationDate = operationDate;
	this.amount = amount;
	this.account = account;
}

public Long getNumber() {
	return number;
}

public void setNumber(Long number) {
	this.number = number;
}

public Date getOperationDate() {
	return operationDate;
}

public void setOperationDate(Date operationDate) {
	this.operationDate = operationDate;
}

public double getAmount() {
	return amount;
}

public void setAmount(double amount) {
	this.amount = amount;
}

public Account getAccount() {
	return account;
}

public void setAccount(Account account) {
	this.account = account;
}

}
