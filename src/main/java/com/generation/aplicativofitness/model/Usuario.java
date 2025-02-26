package com.generation.aplicativofitness.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table (name = "tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank (message = "O campo Nome é obrigatório!")
	@Size (min = 10, max = 100, message = "Digite no mínimo 10 e no máximo 100 caracteres.")
	private String nome;
	
	@Size (max = 500, message = "O link da foto não pode ser maior do que 255 caracteres.")
	private String foto;
	
	@NotNull(message = "A Data é obrigatório!")
	@Column
	private LocalDate data;
	
	@Schema(example = "email@email.com.br")
	@NotNull (message = "O atributo Login é obrigatório!")
	@Email (message = "O atributo Login deve ser um email válido!")
	private String usuario;
	
	@NotBlank (message = "O campo Senha é obrigatório!")
	@Size (min = 8, message = "O campo Senha deve ter no mínimo 8 caracteres.")
	private String senha;
	
	@NotNull (message = "Este campo é obrigatório!")
	@Column (precision = 5, scale = 2)
	private BigDecimal peso;

	@NotNull (message = "Este campo é obrigatório!")
	@Column (precision = 3, scale = 2)
	private BigDecimal altura;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties ("usuario")
	private List<Treino> treino;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}
	
	
	public List<Treino> getTreino() {
		return this.treino;
	}

	public void setTreino(List<Treino> treino) {
		this.treino = treino;
	}

}
