package com.generation.lojadegames.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.generation.lojadegames.Repository.CategoriaRepository;
import com.generation.lojadegames.models.Categoria;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<List<Categoria>> getByNome(@PathVariable String titulo) {
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}

	@PostMapping
	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
	}

	@PutMapping
	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
		return repository.findById(categoria.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Categoria> categoria = repository.findById(id);

		if (categoria.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);

		repository.deleteById(id);
	}
}
//import java.util.List;
//import java.util.Optional;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.server.ResponseStatusException;
//
//import com.generation.lojadegames.Repository.CategoriaRepository;
//import com.generation.lojadegames.models.Categoria;
//
//@RestController
//@RequestMapping(value = "/categorias")
//public class CategoriaController {
//
//	@Autowired
//	private CategoriaRepository repository;
//
//	@GetMapping
//	public ResponseEntity<List<Categoria>> getAll() {
//		return ResponseEntity.ok().body(repository.findAll());
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Categoria> getById(@PathVariable Long id) {
//		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
//				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//	}
//
//	@GetMapping("/titulo/{titulo}")
//	public ResponseEntity<List<Categoria>> getByNome(@PathVariable String titulo) {
//		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
//	}
//
//	@PostMapping
//	public ResponseEntity<Categoria> post(@Valid @RequestBody Categoria categoria) {
//		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria));
//	}
//
//	@PutMapping
//	public ResponseEntity<Categoria> put(@Valid @RequestBody Categoria categoria) {
//		return repository.findById(categoria.getId())
//				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(repository.save(categoria)))
//				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//	}
//
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		Optional<Categoria> categoria = repository.findById(id);
//
//		if (categoria.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//		repository.deleteById(id);
//	}
//}
