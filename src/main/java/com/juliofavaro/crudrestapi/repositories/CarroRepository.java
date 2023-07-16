package com.juliofavaro.crudrestapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juliofavaro.crudrestapi.entities.Carro;

public interface CarroRepository extends JpaRepository<Carro, Long>{
	
}
