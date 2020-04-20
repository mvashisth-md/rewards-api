package com.santosh.rewards.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.santosh.rewards.domain.Transaction;
import com.santosh.rewards.exception.TransactionNotFoundException;
import com.santosh.rewards.model.Reward;
import com.santosh.rewards.repository.TransactionRepository;
import com.santosh.rewards.service.RewardService;
import com.santosh.rewards.util.RewardUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


/**
 * An implementation of RewardService, provides method to get transaction rewards
 *
 * @author Santosh Singh
 */
@Service
public class RewardServiceImpl implements RewardService {

	private static final Logger LOGGER = LoggerFactory.getLogger(RewardServiceImpl.class);
			
    @Autowired
    private TransactionRepository transactionRepository;

    /***
     * Returns reward for by month and total rewards of a given customer id
     * @param customerId Id of the customer
     * @return Reward
     * @throws TransactionNotFoundException 
     */
    public Reward getCustomerRewards(String customerId) throws TransactionNotFoundException {
    	
    	LOGGER.debug("Inside getCustomerRewards() for customerId {}", customerId);
        List<Transaction> transList = transactionRepository.findByCustomerId(customerId.trim().toUpperCase());
        
        //Throwing this exception so response will be 404 instead of rewards with zero value 
        if(CollectionUtils.isEmpty(transList)) {
        	throw new TransactionNotFoundException("Not Found");
        }
                
        Map<String, Integer> monthlyRewardsMap = convertListToMonthlyRewardsMap(transList);
        
        int totalRewards = getTotalReward(monthlyRewardsMap);
        
        return new Reward(customerId, monthlyRewardsMap, totalRewards);
    }
    
    /**
     * Returns rewards for all customer by month and total
     * Note: In real scenario there there are huge customer base there will/may be different approach instead of
     * returning all customers data in a single call 
     * 
     * @return List<Reward> 
     */
    public List<Reward> getAllCustomersRewards(){
    	
    	List<Reward> allRewardList = new ArrayList<>();
    	List<Transaction> allTransList = transactionRepository.findAll();
    	
        Map<String, List<Transaction>> transListByCustId = allTransList.stream()
        		.collect(Collectors.groupingBy(transaction ->transaction.getCustomerId(), Collectors.toList() ) );
    	
        
        transListByCustId.forEach((customerId, transList) -> {
        	
            Map<String, Integer> monthlyRewardsMap = convertListToMonthlyRewardsMap(transList);
            
            int totalRewards = getTotalReward(monthlyRewardsMap);
            
            allRewardList.add( new Reward(customerId, monthlyRewardsMap, totalRewards));
        });
        
    	return allRewardList;
    }
    
  
    /**
     * Converts transaction data in List to map, grouping by month and calculating rewards  
     * @param transList
     * @return Map<String, Integer> where key is month and value is total monthly rewards
     */
    private Map<String, Integer> convertListToMonthlyRewardsMap(List<Transaction> transList){
    	
    	return transList.stream().collect(Collectors.groupingBy(transaction -> RewardUtil.getYearMonth(transaction.getTransactionTs()),
                Collectors.summingInt(transaction -> RewardUtil.calculateReward(transaction.getAmount() )) ));
    }
    
    /**
     * Iterates map as stream and returns total rewards
     * @param monthlyRewardsMap
     * @return
     */
    private int getTotalReward(Map<String, Integer> monthlyRewardsMap){
    	
    	return monthlyRewardsMap.values().stream().mapToInt(reward -> reward.intValue()).sum();
    }
}
