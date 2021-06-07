package br.com.zupacademy.bruno.mercadolivre.detalhesProduto;

public class CaracteristicasResponse {

	private String nome;

	private String descricao;

	public CaracteristicasResponse(String nome, String descricao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
	}

	public String getNome() {
		return nome;
	}

	public String getDescricao() {
		return descricao;
	}

}
