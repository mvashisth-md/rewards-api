package com.santosh.rewards.model;


import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Model class to map input Transaction data in request
 *
 * @author Santosh Singh
 */

public class TransactionData {
    
    //private String customerId;
	private Float amount;
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone="America/Chicago")
    private Timestamp transactionTs;


    public TransactionData() {
    }

    public Float getAmount() {
		return amount;
	}

	public Timestamp getTransactionTs() {
		return transactionTs;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public void setTransactionTs(Timestamp transactionTs) {
		this.transactionTs = transactionTs;
	}

	@Override
	public String toString() {
		return "TransactionData [amount=" + amount + ", transactionTs=" + transactionTs
				+ "]";
	}
}
