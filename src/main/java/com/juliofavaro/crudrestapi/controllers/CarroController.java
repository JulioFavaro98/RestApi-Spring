package com.juliofavaro.crudrestapi.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.juliofavaro.crudrestapi.entities.Carro;
import com.juliofavaro.crudrestapi.repositories.CarroRepository;

@RestController
public class CarroController {
	
	@Autowired
	CarroRepository repository;

	@GetMapping("/carro")
	public List<Carro> getAllCarros(){
		return repository.findAll();
	}
	
	@GetMapping("/carro/{id}")
	public Optional<Carro> getCarroById(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/carro")
	public Carro criarCarro(@RequestBody Carro carro) {
		return repository.save(carro);
	}
	
	@PutMapping("/carro/{id}")
	public ResponseEntity<Carro> atualizarCarro(@PathVariable Long id, @RequestBody Carro carroAtualizado) {
		return repository.findById(id)
				.map(carro -> {
					carro.setMarca(carroAtualizado.getMarca());
					carro.setModelo(carroAtualizado.getModelo());
					carro.setAno(carroAtualizado.getAno());
					
					Carro carroAtualizadoBd = repository.save(carro);
					return ResponseEntity.ok(carroAtualizadoBd);
				})
				.orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/carro/{id}")
	public void deleteCarro(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
