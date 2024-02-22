package com.pickpayclone.services;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.pickpayclone.domain.Transaction;
import com.pickpayclone.domain.User;
import com.pickpayclone.dtos.TransactionDTO;
import com.pickpayclone.repositories.TransactionRepository;

@Service
public class TransactionService {
	@Autowired
	private UserService userService;
	
	@Autowired
	private TransactionRepository repository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NotificationService notificationService;
	
	public Transaction createTransaction(TransactionDTO transaction) throws Exception {
		User sender = this.userService.findUserById(transaction.senderId());
		User reciver = this.userService.findUserById(transaction.reciverId());
		userService.validateTransaction(sender, transaction.value());
		
		boolean isAuthorized = this.authorizateTransaction(sender, transaction.value());
		if(!isAuthorized) {
			throw new Exception("Transação não autorizada");
		}
		
		Transaction newTransaction = new Transaction();
		newTransaction.setAmount(transaction.value());
		newTransaction.setSender(sender);
		newTransaction.setReciver(reciver);
		newTransaction.setTimestamp(LocalDateTime.now());

		sender.setBalance(sender.getBalance().subtract(transaction.value()));
		reciver.setBalance(reciver.getBalance().add(transaction.value()));
		
		this.repository.save(newTransaction);
		this.userService.saveUser(sender);
		this.userService.saveUser(reciver);
		
		this.notificationService.sendNotification(sender, "Transação realizada com sucesso");
		this.notificationService.sendNotification(reciver, "Transação recebida com sucesso");
		
		return newTransaction;
	}
	public boolean authorizateTransaction(User sender, BigDecimal value) {
		ResponseEntity<Map> authorizationResponse= restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
		if(authorizationResponse.getStatusCode()==HttpStatus.OK) {
			String message = (String) authorizationResponse.getBody().get("message");
			return "Autorizado".equalsIgnoreCase(message);
		}else return false;
	}
}
