package com.generation.aplicativofitness.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.generation.aplicativofitness.model.TipoTreino;
import com.generation.aplicativofitness.repository.TipoTreinoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipoTreino")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoTreinoController {

	@Autowired
	private TipoTreinoRepository tipoTreinoRepository;

	@GetMapping
	public ResponseEntity<List<TipoTreino>> getAll() {
		return ResponseEntity.ok(tipoTreinoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<TipoTreino> getById(@PathVariable Long id) {
		return tipoTreinoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<TipoTreino>> getByTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(tipoTreinoRepository.findAllByTipoContainingIgnoreCase(tipo));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<TipoTreino> post(@Valid @RequestBody TipoTreino tipoTreino) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoTreinoRepository.save(tipoTreino));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<TipoTreino> put(@Valid @RequestBody TipoTreino tipoTreino) {
		return tipoTreinoRepository.findById(tipoTreino.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(tipoTreinoRepository.save(tipoTreino)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<TipoTreino> tipoTreino = tipoTreinoRepository.findById(id);

		if (tipoTreino.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		tipoTreinoRepository.deleteById(id);
	}

}