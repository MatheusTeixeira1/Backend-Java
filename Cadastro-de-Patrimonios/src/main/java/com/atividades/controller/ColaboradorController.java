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

import com.atividades.modelos.Colaborador;
import com.atividades.repository.ColaboradorRepository;


@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class ColaboradorController {
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@RequestMapping(value = "/colaborador", method = RequestMethod.GET)
	public List<Colaborador> Get() {
		return colaboradorRepository.findAll();
	}

	@RequestMapping(value = "colaborador/{id}", method = RequestMethod.GET)
	public ResponseEntity<Colaborador> GetById(@PathVariable(value = "id") long id) {
		Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
		if (colaborador.isPresent()) {
			return new ResponseEntity<Colaborador>(colaborador.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/colaborador", method = RequestMethod.POST)
	public Colaborador Post(@RequestBody Colaborador colaborador) {
		return colaboradorRepository.save(colaborador);
	}

	@RequestMapping(value = "/colaborador/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Colaborador> Put(@PathVariable(value = "id") long id, @RequestBody Colaborador newColaborador) {
		Optional<Colaborador> oldColaborador = colaboradorRepository.findById(id);
		if (oldColaborador.isPresent()) {
			Colaborador colaborador = oldColaborador.get();
			colaborador.setNome(newColaborador.getNome());
			colaborador.setDepartamento(newColaborador.getDepartamento());
			colaborador.setCpf(newColaborador.getCpf());
			
			colaboradorRepository.save(colaborador);
			return new ResponseEntity<Colaborador>(colaborador, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/colaborador/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	{
	    Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
	    if (colaborador.isPresent()) {
	    	colaboradorRepository.delete(colaborador.get());
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
