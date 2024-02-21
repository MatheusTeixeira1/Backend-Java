package com.pickpayclone.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pickpayclone.domain.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	
}
