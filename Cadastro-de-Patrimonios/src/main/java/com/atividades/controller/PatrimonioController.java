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

import com.atividades.repository.PatrimonioRepository;
import com.atividades.modelos.Patrimonio;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class PatrimonioController {
	@Autowired
	private PatrimonioRepository patrimonioRepository;

	@RequestMapping(value = "/patrimonio", method = RequestMethod.GET)
	public List<Patrimonio> Get() {
		return patrimonioRepository.findAll();
	}

	@RequestMapping(value = "patrimonio/{id}", method = RequestMethod.GET)
	public ResponseEntity<Patrimonio> GetById(@PathVariable(value = "id") long id) {
		Optional<Patrimonio> patrimonio = patrimonioRepository.findById(id);
		if (patrimonio.isPresent()) {
			return new ResponseEntity<Patrimonio>(patrimonio.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/patrimonio", method = RequestMethod.POST)
	public Patrimonio Post(@RequestBody Patrimonio patrimonio) {
		return patrimonioRepository.save(patrimonio);
	}

	@RequestMapping(value = "/patrimonio/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Patrimonio> Put(@PathVariable(value = "id") long id, @RequestBody Patrimonio newPatrimonio) {
		Optional<Patrimonio> oldPatrimonio = patrimonioRepository.findById(id);
		if (oldPatrimonio.isPresent()) {
			Patrimonio patrimonio = oldPatrimonio.get();
			patrimonio.setCodBem(newPatrimonio.getCodBem());
			patrimonio.setDescricao(newPatrimonio.getDescricao());
			patrimonio.setDetalhamento(newPatrimonio.getDetalhamento());
			patrimonio.setPreco(newPatrimonio.getPreco());

			patrimonioRepository.save(patrimonio);
			return new ResponseEntity<Patrimonio>(patrimonio, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/patrimonio/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	{
	    Optional<Patrimonio> patrimonio = patrimonioRepository.findById(id);
	    if (patrimonio.isPresent()) {
	    	patrimonioRepository.delete(patrimonio.get());
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
