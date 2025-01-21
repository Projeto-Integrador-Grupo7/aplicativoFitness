package com.generation.aplicativofitness.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.aplicativofitness.model.Treino;

public interface TreinoRepository extends JpaRepository<Treino, Long> {

	public List <Treino> findAllByExerciciosContainingIgnoreCase(@Param("exercicios") String exercicios);
}
