package com.santosh.rewards.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import java.sql.Timestamp;

/**
 * Entity class for Transaction table
 *
 * @author Santosh Singh
 */

@Entity
@Table(name = "Transaction")

public class Transaction {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerId;
	private Float amount;
    private Timestamp transactionTs;


    public Transaction() {
    }

    public Float getAmount() {
		return amount;
	}

	public Timestamp getTransactionTs() {
		return transactionTs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public void setTransactionTs(Timestamp transactionTs) {
		this.transactionTs = transactionTs;
	}


	@Override
	public String toString() {
		return "Transaction [id=" + id + ", customerId=" + customerId + ", amount=" + amount + ", transactionTs="
				+ transactionTs + "]";
	}
	
	
}
