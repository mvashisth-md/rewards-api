package com.santosh.rewards.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.santosh.rewards.model.TransactionData;
import com.santosh.rewards.service.TransactionService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


/**
 * Controller class to handle customer transaction request
 *
 * @author Santosh Singh
 */
@RestController
public class TransactionController {
	
    @Autowired
    private TransactionService transactionService;

    //Note: Accepting transaction timestamp in request so that test data can be created for previous month
    /**
     * This end point is for testing purpose for this specific exercise/problem statement.
     * Saves additional transaction data to reflect dynamic testing of reward service 
     * @param transData
     * @return
     */
    
    @RequestMapping(value="/customers/{customerId}/transaction", method= RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Save customer's transaction to create more test data.")
    
    public ResponseEntity<String> createCustomerTransaction(@ApiParam(value = "Customer Id",required = true)
            @PathVariable(name = "customerId") String customerId, @RequestBody(required = true) TransactionData transactionData) {
        
    	transactionService.createCustomerTransaction(customerId, transactionData);
    	
    	//Normally we should return id of created resource but this end point is just for testing
    	return new ResponseEntity<String>(HttpStatus.CREATED) ;
    }


}