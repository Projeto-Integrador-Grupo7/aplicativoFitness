package com.generation.aplicativofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.aplicativofitness.model.TipoTreino;

public interface TipoTreinoRepository extends JpaRepository <TipoTreino, Long> {
	
	public List<TipoTreino> findAllByTipoContainingIgnoreCase(@Param("tipo") String tipo);

}
