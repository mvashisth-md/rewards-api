package com.santosh.rewards.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.santosh.rewards.domain.Transaction;

import java.util.List;

/**
 * Transaction Repository to fetch Transactions by given criteria
 *
 * @author Santosh Singh
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
    List<Transaction> findByCustomerId(String customerId);
    
    List<Transaction> findAll();
}
