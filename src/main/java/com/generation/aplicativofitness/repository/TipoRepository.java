package com.generation.aplicativofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.aplicativofitness.model.Tipo;

public interface TipoRepository extends JpaRepository <Tipo, Long> {
	
	public List<Tipo> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);

}
