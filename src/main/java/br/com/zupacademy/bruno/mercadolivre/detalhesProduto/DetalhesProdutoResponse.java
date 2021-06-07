package br.com.zupacademy.bruno.mercadolivre.detalhesProduto;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Caracteristica;
import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.opniaoProduto.Opniao;

public class DetalhesProdutoResponse {

	private String nome;

	private BigDecimal valor;

	private String descricao;

	private String nomeCategoria;

	private List<CaracteristicasResponse> caracterisiticas;

	private List<String> imagens;

	private List<String> perguntas;

	private String notaMedia;

	private List<OpnioesResponse> opnioes;

	public DetalhesProdutoResponse(Produto produto) {
		super();
		this.nome = produto.getNome();
		this.valor = produto.getValor();
		this.descricao = produto.getDescricao();
		this.nomeCategoria = produto.getCategoria_Nome();
		this.caracterisiticas = this.toModelCaracteristicas(produto.getCaracteristicas());
		this.imagens = produto.getUrlImagens_Link();
		this.perguntas = produto.getPerguntas_Titulo();
		this.opnioes = this.toModelOpnioes(produto.getOpnioes());
		this.notaMedia = produto.getNotaMedia();

	}

	private List<OpnioesResponse> toModelOpnioes(List<Opniao> listaOpnioes) {
		return listaOpnioes.stream().map(op -> new OpnioesResponse(op.getNota(), op.getTitulo(), op.getDescricao()))
				.collect(Collectors.toList());
	}

	private List<CaracteristicasResponse> toModelCaracteristicas(List<Caracteristica> listaCaracteristicas) {
		return listaCaracteristicas.stream().map(c -> new CaracteristicasResponse(c.getNome(), c.getDescricao()))
				.collect(Collectors.toList());
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getNomeCategoria() {
		return nomeCategoria;
	}

	public List<CaracteristicasResponse> getCaracterisiticas() {
		return caracterisiticas;
	}

	public List<String> getImagens() {
		return imagens;
	}

	public List<String> getPerguntas() {
		return perguntas;
	}

	public List<OpnioesResponse> getOpnioes() {
		return opnioes;
	}

	public String getNotaMedia() {
		return notaMedia;
	}

}
