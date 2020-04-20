package com.santosh.rewards.model;

import java.util.Map;


/**
 * DTO class to return response to client as JSON
 *
 * @author Santosh Singh
 */

public class Reward {

	private String customerId;
    private Map<String, Integer> monthlyRewards;
    private int totalRewards;

    public Reward(String customerId, Map<String, Integer> monthlyRewards, int totalRewards) {
    	
    	this.customerId = customerId;
        this.monthlyRewards = monthlyRewards;
        this.totalRewards = totalRewards;
    }

	public Map<String, Integer> getMonthlyRewards() {
		return monthlyRewards;
	}

	public int getTotalRewards() {
		return totalRewards;
	}

	public String getCustomerId() {
		return customerId;
	}

	
}
