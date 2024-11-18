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

import com.atividades.modelos.Blueray;
import com.atividades.repository.BluerayRepository;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class BluerayController {
	@Autowired
	private BluerayRepository bluerayRepository;

	@RequestMapping(value = "/blueray", method = RequestMethod.GET)
	public List<Blueray> Get() {
		return bluerayRepository.findAll();
	}

	@RequestMapping(value = "blueray/{id}", method = RequestMethod.GET)
	public ResponseEntity<Blueray> GetById(@PathVariable(value = "id") long id) {
		Optional<Blueray> blueray = bluerayRepository.findById(id);
		if (blueray.isPresent()) {
			return new ResponseEntity<Blueray>(blueray.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/blueray", method = RequestMethod.POST)
	public Blueray Post(@RequestBody Blueray blueray) {
		return bluerayRepository.save(blueray);
	}

	@RequestMapping(value = "/blueray/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Blueray> Put(@PathVariable(value = "id") long id, @RequestBody Blueray newBlueray) {
		Optional<Blueray> oldBlueray = bluerayRepository.findById(id);
		if (oldBlueray.isPresent()) {
			Blueray blueray = oldBlueray.get();
			blueray.setNome(newBlueray.getNome());
			blueray.setQuantidade(newBlueray.getQuantidade());
			blueray.setPreco(newBlueray.getPreco());
			blueray.setImagem(newBlueray.getImagem());
			
			bluerayRepository.save(blueray);
			return new ResponseEntity<Blueray>(blueray, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(value = "/blueray/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id)
	{
	    Optional<Blueray> blueray = bluerayRepository.findById(id);
	    if (blueray.isPresent()) {
	    	bluerayRepository.delete(blueray.get());
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
	}
}
