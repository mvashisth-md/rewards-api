package com.santosh.rewards.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.santosh.rewards.domain.Transaction;
import com.santosh.rewards.exception.TransactionNotFoundException;
import com.santosh.rewards.model.Reward;
import com.santosh.rewards.repository.TransactionRepository;
import com.santosh.rewards.service.impl.RewardServiceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * 
 * Test class for RewardService
 * @author Santosh Singh
 *
 */
public class RewardServiceTest {
	
    @InjectMocks
    RewardServiceImpl service;

    @Mock
    TransactionRepository repository  ;

    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @DisplayName("Get customer reward test by customer Id") 
    @Test
    public void getCustomerRewardsTest() throws TransactionNotFoundException {

        Map<String, Integer> expectedMonthlyRewards = new HashMap<>();
        expectedMonthlyRewards.put("2020-04", 450);
        int expectedTotalRewards = 450;
        String custId = "C1";
        List<Transaction> transactions = prepareTransactionList(Timestamp.valueOf("2020-04-19 10:20:30.678"), custId);

        when(repository.findByCustomerId(custId)).thenReturn(transactions);

        Reward rewards = service.getCustomerRewards(custId);

        assertEquals(expectedTotalRewards, rewards.getTotalRewards(),"Expected monthly rewards did not match");
        assertEquals(expectedMonthlyRewards, rewards.getMonthlyRewards(), "Expected total rewards did not match");

    }
    
    @DisplayName("Get all customer rewards test") 
    @Test
    public void getAllCustomerRewardsTest() {

        Map<String, Integer> expectedMonthlyRewards = new HashMap<>();
        expectedMonthlyRewards.put("2020-04", 450);
        int expectedTotalRewards = 450;
        String custId = "C1";
        List<Transaction> transList1 = prepareTransactionList(Timestamp.valueOf("2020-04-19 10:20:30.678"), custId);
        
        List<Transaction> transList2 = prepareTransactionList(Timestamp.valueOf("2020-04-19 10:20:30.678"), "C2");

        when(repository.findAll()).thenReturn(Stream.concat(transList1.stream(), transList2.stream()).collect(Collectors.toList()));

        List<Reward> rewardsList = service.getAllCustomersRewards();

        List<Reward> rewardsOfOneCust = rewardsList.stream().filter( r -> r.getCustomerId().equalsIgnoreCase("C1")).collect(Collectors.toList());
        Reward rewards = rewardsOfOneCust.get(0); 
        assertEquals(1, rewardsOfOneCust.size(), "Size should be one");
        assertEquals(expectedTotalRewards, rewards.getTotalRewards(),"Expected monthly rewards did not match");
        assertEquals(expectedMonthlyRewards, rewards.getMonthlyRewards(), "Expected total rewards did not match");

    }
    private List<Transaction> prepareTransactionList(Timestamp ts, String custId){
    	
    	List<Transaction> transList = new ArrayList<>();
    	
    	Float amount = 50F;
    	Transaction trans = null;
    	//create some test transaction records
    	for(int i=1; i <=4 ; i++) {
    		
	        trans = new Transaction();
	        trans.setAmount(amount*i);
	        trans.setCustomerId(custId);
	        trans.setTransactionTs(ts);
	        
	        transList.add(trans);
    	}
    	
    	return transList;
    }
}