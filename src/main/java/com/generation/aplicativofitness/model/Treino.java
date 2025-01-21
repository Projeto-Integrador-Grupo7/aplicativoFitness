package com.generation.aplicativofitness.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
//import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity							//Indicar que a classe é uma tabela
@Table(name = "tb_treino")	// indica o nome da tabela no bd
public class Treino {

    @Id // Ele é a chave primária (PRIMARY KEY)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto increment
    private Long id;

    @NotBlank(message = "Esse campo é obrigatório")
    @Size(min = 5, max = 100, message = "Digite no mínimo 05 e no máximo 100 caracteres")
    private String exercicios;

    @NotBlank(message = "Esse campo é obrigatório")
    @Size(min = 3, max = 10, message = "Digite no mínimo 3 e no máximo 10 caracteres")
    @Pattern(regexp = "\\d+x\\d+", message = "O formato deve ser 'SériesxRepetições', exemplo: 3x12")
    private String seriesRepeticao;

    private String descanso; // Agora é uma String que armazena "00:30"
    private String tempoTreino; // Também como String "02:00"


    @ManyToOne
    @JsonIgnoreProperties("treino")
    private TipoTreino tipoTreino;

    @ManyToOne
    @JsonIgnoreProperties("treino")
    private Usuario usuario;

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExercicios() {
        return exercicios;
    }

    public void setExercicios(String exercicios) {
        this.exercicios = exercicios;
    }

    public String getSeriesRepeticao() {
        return seriesRepeticao;
    }

    public void setSeriesRepeticao(String seriesRepeticao) {
        this.seriesRepeticao = seriesRepeticao;
    }

    public String getDescanso() {
        return descanso;
    }

    public void setDescanso(String descanso) {
        this.descanso = descanso;
    }

    public String getTempoTreino() {
        return tempoTreino;
    }

    public void setTempoTreino(String tempoTreino) {
        this.tempoTreino = tempoTreino;
    }

    
    public TipoTreino getTipoTreino() {
        return tipoTreino;
    }

    public void setTipoTreino(TipoTreino tipoTreino) {
        this.tipoTreino = tipoTreino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
