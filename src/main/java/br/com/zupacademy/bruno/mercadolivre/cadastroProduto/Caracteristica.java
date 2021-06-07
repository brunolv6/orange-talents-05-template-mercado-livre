package br.com.zupacademy.bruno.mercadolivre.cadastroProduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Caracteristica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@NotBlank
	private String descricao;

	@Deprecated
	public Caracteristica() {
	}

	public Caracteristica(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Caracteristica [id=" + id + ", nome=" + nome + ", descricao=" + descricao + "]";
	}

}
