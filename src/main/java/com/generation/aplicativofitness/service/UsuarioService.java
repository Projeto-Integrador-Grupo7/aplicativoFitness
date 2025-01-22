package com.generation.aplicativofitness.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.generation.aplicativofitness.model.Usuario;
import com.generation.aplicativofitness.repository.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Optional<BigDecimal> calcularIMC(Long id) {
	    Optional<Usuario> usuario = usuarioRepository.findById(id);

	    if (usuario.isPresent()) {
	        BigDecimal peso = usuario.get().getPeso();
	        BigDecimal altura = usuario.get().getAltura();

	        // Garantir que peso e altura sejam maiores que zero
	        if (peso.compareTo(BigDecimal.ZERO) > 0 && altura.compareTo(BigDecimal.ZERO) > 0) {
	            BigDecimal alturaSquared = altura.multiply(altura);
	            BigDecimal imc = peso.divide(alturaSquared, MathContext.DECIMAL64);

	            // Ajustar o IMC para ter 2 casas decimais
	            BigDecimal imcComDuasCasas = imc.setScale(2, RoundingMode.HALF_UP);

	            return Optional.of(imcComDuasCasas);
	        }
	    }

	    return Optional.empty();
	}
	
	public Optional<Usuario> cadastrarUsuario(Usuario usuario) {

		if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent())
			return Optional.empty();

		return Optional.of(usuarioRepository.save(usuario));
	
	}

	public Optional<Usuario> atualizarUsuario(Usuario usuario) {
		
		if(usuarioRepository.findById(usuario.getId()).isPresent()) {

			Optional<Usuario> buscaUsuario = usuarioRepository.findByUsuario(usuario.getUsuario());

			if ( (buscaUsuario.isPresent()) && ( buscaUsuario.get().getId() != usuario.getId()))
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário já existe!", null);

			return Optional.ofNullable(usuarioRepository.save(usuario));
			
		}
		
		return Optional.empty();
	
	}
}
	

