package com.pickpayclone.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pickpayclone.domain.User;
import com.pickpayclone.domain.UserType;
import com.pickpayclone.dtos.UserDTO;
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
	public User createUser(UserDTO data) {
		User newUser = new User(data);
		this.saveUser(newUser);
		return newUser;
	}
	
	public List<User> getAllUsers(){
		return this.reposiory.findAll();
	}
}
