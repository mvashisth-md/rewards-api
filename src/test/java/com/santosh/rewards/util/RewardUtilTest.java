package com.santosh.rewards.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.santosh.rewards.util.RewardUtil;

class RewardUtilTest {

	@DisplayName("Calculate reward test for amount upto 50") 
	@Test
	void testCalculateReward_Upto50() {
		
		int reward = RewardUtil.calculateReward(50f);
		assertEquals(0, reward, "Reward should be zero");
	}
	
	@DisplayName("Calculate reward test for amount zero") 
	@Test
	void testCalculateReward_zero() {
		
		int reward = RewardUtil.calculateReward(0f);
		assertEquals(0, reward, "Reward should be zero");
	}
	
	@DisplayName("Calculate reward test for amount upto 100") 
	@Test
	void testCalculateReward_Upto100() {
		
		Float transValue = 90f;
		int reward = RewardUtil.calculateReward(transValue);
		assertEquals((transValue.intValue() - 50), reward, "Reward does not match");
	}
	
	@DisplayName("Calculate reward test for amount above 100") 
	@Test
	void testCalculateReward_Above100() {
		
		Float transValue = 120f;
		int reward = RewardUtil.calculateReward(transValue);
		assertEquals(((transValue.intValue()- 100 )*2 + 50), reward, "Reward does not match");
	}
	

}
