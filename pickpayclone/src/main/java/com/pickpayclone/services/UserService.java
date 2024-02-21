package com.pickpayclone.services;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickpayclone.domain.User;
import com.pickpayclone.domain.UserType;
import com.pickpayclone.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository reposiory;
	
	public void validateTransaction(User sender, BigDecimal amount) throws Exception{
		if(sender.getUserType() == UserType.MERCHANT) {
			throw new Exception("Usuario do tipo lojista não esta autorizado a realizar transação");
		}
		
		if(sender.getBalance().compareTo(amount)<0) {
			throw new Exception("Saldo insuficiente");
		}
	}
	
	public User findUserById(Long id) throws Exception{
		return this.reposiory.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado"));
	}
	
	public void saveUser(User user) {
		this.reposiory.save(user);
	}
}
