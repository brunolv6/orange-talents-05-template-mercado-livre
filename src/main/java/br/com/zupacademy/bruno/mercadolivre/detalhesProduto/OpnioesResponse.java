package br.com.zupacademy.bruno.mercadolivre.detalhesProduto;

public class OpnioesResponse {

	private Integer nota;

	private String titulo;

	private String descricao;

	public OpnioesResponse(Integer nota, String titulo, String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	public Integer getNota() {
		return nota;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getDescricao() {
		return descricao;
	}

}
