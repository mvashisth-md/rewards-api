package com.santosh.rewards.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.santosh.rewards.domain.Transaction;
import com.santosh.rewards.model.TransactionData;
import com.santosh.rewards.repository.TransactionRepository;
import com.santosh.rewards.service.TransactionService;


/**
 * An implementation of TransactionService, provides method to save new customer transaction
 *
 * @author Santosh Singh
 */
@Service
public class TransactionServiceImpl implements TransactionService {

	private static final Logger LOGGER = LoggerFactory.getLogger(TransactionServiceImpl.class);
			
    @Autowired
    private TransactionRepository transactionRepository;

    /***
     * Saves new transaction data
     * @param TransactionData in request
     * 
     */	
    public void createCustomerTransaction(String customerId, TransactionData transData) {
    	
    	Transaction transEntity = new Transaction();
    	
    	BeanUtils.copyProperties(transData, transEntity);
    	transEntity.setCustomerId(customerId.trim().toUpperCase());
    	//Save and flush
    	transactionRepository.saveAndFlush(transEntity);
    }
    

}
