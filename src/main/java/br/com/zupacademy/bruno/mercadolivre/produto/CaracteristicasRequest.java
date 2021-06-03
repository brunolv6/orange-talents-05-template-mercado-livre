package br.com.zupacademy.bruno.mercadolivre.produto;

import javax.validation.constraints.NotBlank;

public class CaracteristicasRequest {

	@NotBlank
	private String nome;

	@NotBlank // não funciona
	private String descricao;

	public CaracteristicasRequest(@NotBlank String nome, @NotBlank String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public Caracteristica toModel() {
		return new Caracteristica(this.nome, this.descricao);
	}
}
