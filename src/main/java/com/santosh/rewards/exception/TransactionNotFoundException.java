package com.santosh.rewards.exception;

/**
 * Custom Exception class to use when transaction is not found for customer id
 * @author Santosh Singh
 *
 */
public class TransactionNotFoundException extends Exception { 
    
	
	public TransactionNotFoundException(String errorMessage) {
        super(errorMessage);
    }

}
