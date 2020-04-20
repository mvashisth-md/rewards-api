package com.santosh.rewards.service;

import com.santosh.rewards.model.TransactionData;


/**
 * An interface which defines method to save new customer transaction
 *
 * @author Santosh Singh
 */
public interface TransactionService {

    /***
     * Saves new transaction
     * @param TransactionData in request
     * 
     */	
    public void createCustomerTransaction(String customerId, TransactionData transData);
    
}



