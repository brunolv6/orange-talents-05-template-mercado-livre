//package br.com.zupacademy.bruno.mercadolivre.pedido;
//
//import java.math.BigDecimal;
//import java.util.UUID;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToOne;
//import javax.validation.constraints.NotNull;
//
//import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
//import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
//
//@Entity
//public class Pedido {
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@NotNull
//	@Column(unique = true)
//	private UUID idPedido = UUID.randomUUID();
//
//	@NotNull
//	@ManyToOne
//	private Produto produto;
//
//	@NotNull
//	@ManyToOne
//	private Usuario comprador;
//
//	@NotNull
//	private BigDecimal valorProduto;
//
//	@NotNull
//	private Integer quantidadeProduto;
//
//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private FormaDePagamento formaDePagamento;
//
//	@NotNull
//	@Enumerated(EnumType.STRING)
//	private Status status = Status.INICIADA;
//
//	@Deprecated
//	public Pedido() {
//		super();
//	}
//
//	public Pedido(@NotNull Produto produto, @NotNull Usuario comprador, @NotNull Integer quantidadeProduto,
//			@NotNull FormaDePagamento formaDePagamento) {
//		super();
//		this.produto = produto;
//		this.comprador = comprador;
//		this.valorProduto = produto.getValor();
//		this.quantidadeProduto = quantidadeProduto;
//		this.formaDePagamento = formaDePagamento;
//	}
//
//	public UUID getIdPedido() {
//		return idPedido;
//	}
//
//	@Override
//	public String toString() {
//		return "Pedido [id=" + id + ", idPedido=" + idPedido + ", produto=" + produto + ", comprador=" + comprador
//				+ ", valorProduto=" + valorProduto + ", quantidadeProduto=" + quantidadeProduto + ", formaDePagamento="
//				+ formaDePagamento + ", status=" + status + "]";
//	}
//
//}
