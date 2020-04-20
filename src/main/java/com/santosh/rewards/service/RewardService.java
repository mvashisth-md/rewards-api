package com.santosh.rewards.service;

import java.util.List;

import com.santosh.rewards.exception.TransactionNotFoundException;
import com.santosh.rewards.model.Reward;


/**
 * An interface which defines method to get transaction rewards for requested customerId
 *
 * @author Santosh Singh
 */
public interface RewardService {

    /***
     * Returns reward for by month and total rewards of a given customer id
     * @param customerId Id of the customer
     * @return Reward
     */	
    public Reward getCustomerRewards(String customerId) throws TransactionNotFoundException;
    
    /**
     * Returns rewards for all customer by month and total
     * Note: In real scenario there there are huge customer base there will/may be different approach instead of
     * returning all customers data in a single call 
     * 
     * @return List<Reward> 
     */    
    public List<Reward> getAllCustomersRewards();
}



