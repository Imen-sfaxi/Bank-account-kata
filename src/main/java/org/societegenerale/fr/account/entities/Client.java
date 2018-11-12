package org.societegenerale.fr.account.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Client implements Serializable{

	@Id
    @GeneratedValue
	private Long code;
	private String name;
	private String email;
	
	@OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
	private Collection<Account> accounts;

	public Client(String name, String email) {
		super();
		this.name = name;
		this.email = email;
	}

	public Client() {
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}
