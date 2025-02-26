package com.generation.aplicativofitness.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.aplicativofitness.model.Treino;
import com.generation.aplicativofitness.repository.TipoRepository;
import com.generation.aplicativofitness.repository.TreinoRepository;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/treinos")
public class TreinoController {
	@Autowired
	private TreinoRepository treinoRepository;

	@Autowired
	private TipoRepository tipoRepository;
	
	@GetMapping
	public ResponseEntity<List<Treino>> getAll() {
		return ResponseEntity.ok(treinoRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Treino> getById(@PathVariable Long id) {
		return treinoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());	
	}
	
	@GetMapping("/exercicios/{exercicios}")
	public ResponseEntity<List<Treino>> getByExercicios(@PathVariable String exercicios) {
		return ResponseEntity.ok(treinoRepository.findAllByExerciciosContainingIgnoreCase(exercicios));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Treino> post(@Valid @RequestBody Treino treino) {
	    
		if (treino.getTipo() == null || 
	        !tipoRepository.existsById(treino.getTipo().getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de Treino não existe!");   	
		}
		 return ResponseEntity.status(HttpStatus.CREATED).body(treinoRepository.save(treino));
	}
	
	@PutMapping("/atualizar")
	public ResponseEntity<Treino> put(@Valid @RequestBody Treino treino) {
	    if (!treinoRepository.existsById(treino.getId())) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	    }
	     
	    if (treino.getTipo() == null || 
	        !tipoRepository.existsById(treino.getTipo().getId())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tipo de Treino não existe!");       
	    }
	    
	    return ResponseEntity.status(HttpStatus.OK).body(treinoRepository.save(treino));
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Treino> treino = treinoRepository.findById(id);
		
		if(treino.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		treinoRepository.deleteById(id);
	}
}
