package com.santosh.rewards.util;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A utility class to calculate Rewards and other utility methods
 *
 * @author Santosh Singh
 */
public class RewardUtil {
	
 
	
    /***
     * This function calculates and returns rewards for the provided transaction amount.
     * A customer receives 2 points for every dollar spent over $100 in each transaction,
     * plus 1 point for every dollar spent over $50 in each transaction.
     * (e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).
     * 
     * @param amount amount of a single transaction
     * @return reward earned reward points
     */
    public static int calculateReward(Float amount) {
    	
    	int amountInt = amount.intValue();
    	
        if (amountInt >= 100) {
        	
            return 2 * (amountInt - 100) + 50;
            
        }else if (amountInt > 50) {
        	
            return amountInt - 50;
        }  
        
        return 0;
    }
    
    /***
     * This function extracts month and year from timestamp object
     * @param ts Timestamp object
     * @return A string of format yyyy-MM
     */

    public static String getYearMonth(Timestamp ts) {
    	
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        
        return sdf.format(new Date(ts.getTime()));

    }    
}
