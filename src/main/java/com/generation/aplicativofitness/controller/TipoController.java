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

import com.generation.aplicativofitness.model.Tipo;
import com.generation.aplicativofitness.repository.TipoRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tipo")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TipoController {

	@Autowired
	private TipoRepository tipoRepository;

	@GetMapping
	public ResponseEntity<List<Tipo>> getAll() {
		return ResponseEntity.ok(tipoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tipo> getById(@PathVariable Long id) {
		return tipoRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Tipo>> getByTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(tipoRepository.findAllByTipoContainingIgnoreCase(tipo));
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Tipo> post(@Valid @RequestBody Tipo tipo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(tipoRepository.save(tipo));
	}

	@PutMapping("/atualizar")
	public ResponseEntity<Tipo> put(@Valid @RequestBody Tipo tipo) {
		return tipoRepository.findById(tipo.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(tipoRepository.save(tipo)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tipo> tipo = tipoRepository.findById(id);

		if (tipo.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		tipoRepository.deleteById(id);
	}

}