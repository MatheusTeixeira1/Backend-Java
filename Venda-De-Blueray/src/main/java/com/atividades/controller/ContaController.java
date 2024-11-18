package com.atividades.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atividades.modelos.Conta;
import com.atividades.repository.ContaRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ContaController {
	@Autowired
	private ContaRepository contaRepository;

	@RequestMapping(value = "/conta", method = RequestMethod.GET)
	public List<Conta> Get() {
		return contaRepository.findAll();
	}

	@RequestMapping(value = "conta/{id}", method = RequestMethod.GET)
	public ResponseEntity<Conta> GetById(@PathVariable(value = "id") long id) {
		Optional<Conta> conta = contaRepository.findById(id);
		if (conta.isPresent()) {
			return new ResponseEntity<Conta>(conta.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/conta", method = RequestMethod.POST)
	public Conta Post(@RequestBody Conta conta) {
		return contaRepository.save(conta);
	}

	@RequestMapping(value = "/conta/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Conta> Put(@PathVariable(value = "id") long id, @RequestBody Conta newConta) {
		Optional<Conta> oldConta = contaRepository.findById(id);
		if (oldConta.isPresent()) {
			Conta conta = oldConta.get();
			conta.setNome(newConta.getNome());
			conta.setEmail(newConta.getEmail());
			conta.setSenha(newConta.getSenha());
			conta.setIsAdm(newConta.getIsAdm());
			conta.setImagem(newConta.getImagem());
			
			contaRepository.save(conta);
			return new ResponseEntity<Conta>(conta, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/conta/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	{
	    Optional<Conta> conta = contaRepository.findById(id);
	    if (conta.isPresent()) {
	    	contaRepository.delete(conta.get());
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
