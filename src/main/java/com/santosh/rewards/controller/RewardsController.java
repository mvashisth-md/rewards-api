package com.santosh.rewards.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.rewards.exception.TransactionNotFoundException;
import com.santosh.rewards.model.Reward;
import com.santosh.rewards.service.RewardService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Controller class to handle customer reward api requests
 *
 * @author Santosh Singh
 */
@RestController
public class RewardsController {
	
    @Autowired
    private RewardService rewardService;

    @RequestMapping(value="/customers/{customerId}/rewards", method= RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get customer's reward during a three month period by customer id.")
    public Reward getCustomerRewards(@ApiParam(value = "Customer Id", 
            required = true)
            @PathVariable(name = "customerId") String customerId) throws TransactionNotFoundException{
        
    	return rewardService.getCustomerRewards(customerId);
    }

    @RequestMapping(value="/customers/rewards", method= RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all customer's reward during a three month period.")
    public List<Reward> getAllCustomerRewards() {
        
    	return rewardService.getAllCustomersRewards();
    }

}