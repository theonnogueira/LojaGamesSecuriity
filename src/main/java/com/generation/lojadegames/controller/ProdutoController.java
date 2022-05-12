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
import com.generation.lojadegames.Repository.ProdutosRepository;
import com.generation.lojadegames.models.Produtos;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoController {

	@Autowired
	private ProdutosRepository repository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@GetMapping
	public ResponseEntity<List<Produtos>> getAll() {
		return ResponseEntity.ok().body(repository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produtos> getById(@PathVariable Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}

	@PostMapping
	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto) {
		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
		}

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

	}

	@PutMapping
	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produto) {
		if (repository.existsById(produto.getCategoria().getId())) {
			if (categoriaRepository.existsById(produto.getCategoria().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
			}

			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Produtos> produto = repository.findById(id);

		if (produto.isEmpty())
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
//import com.generation.lojadegames.Repository.ProdutosRepository;
//import com.generation.lojadegames.models.Produtos;
//
//@RestController
//@RequestMapping(value = "/produtos")
//public class ProdutoController {
//
//	@Autowired
//	private ProdutosRepository repository;
//
//	@Autowired
//	private CategoriaRepository categoriaRepository;
//
//	@GetMapping
//	public ResponseEntity<List<Produtos>> getAll() {
//		return ResponseEntity.ok().body(repository.findAll());
//	}
//
//	@GetMapping("/{id}")
//	public ResponseEntity<Produtos> getById(@PathVariable Long id) {
//		return repository.findById(id).map(resp -> ResponseEntity.ok(resp))
//				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
//	}
//
//	@GetMapping("/nome/{nome}")
//	public ResponseEntity<List<Produtos>> getByNome(@PathVariable String nome) {
//		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
//	}
//
//	@PostMapping
//	public ResponseEntity<Produtos> post(@Valid @RequestBody Produtos produto) {
//		if (categoriaRepository.existsById(produto.getCategoria().getId())) {
//			return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
//		}
//
//		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//
//	}
//
//	@PutMapping
//	public ResponseEntity<Produtos> put(@Valid @RequestBody Produtos produto) {
//		if (repository.existsById(produto.getCategoria().getId())) {
//			if (categoriaRepository.existsById(produto.getCategoria().getId())) {
//				return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
//			}
//
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//		}
//
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
//
//	}
//
//	@ResponseStatus(HttpStatus.NO_CONTENT)
//	@DeleteMapping("/{id}")
//	public void delete(@PathVariable Long id) {
//		Optional<Produtos> produto = repository.findById(id);
//
//		if (produto.isEmpty())
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
//
//		repository.deleteById(id);
//	}
//}