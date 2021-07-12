package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.Transacao;

@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//	@NotNull
//	@Column(unique = true)
//	private UUID idPedido = UUID.randomUUID();

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Produto produto;

	@NotNull
	@ManyToOne
	private Usuario comprador;

	@NotNull
	private BigDecimal valorProduto;

	@NotNull
	private Integer quantidadeProduto;

	@NotNull
	@Enumerated(EnumType.STRING)
	private GatewayDePagamento gatewayDePagamento;

	@NotNull
	@Enumerated(EnumType.STRING)
	private Status status = Status.INICIADA;

	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Set<Transacao> transacoes = new HashSet<>();

	@Deprecated
	public Pedido() {
		super();
	}

	public Pedido(@NotNull Produto produto, @NotNull Usuario comprador, @NotNull Integer quantidadeProduto,
			@NotNull GatewayDePagamento gatewayDePagamento) {
		super();
		this.produto = produto;
		this.comprador = comprador;
		this.valorProduto = produto.getValor();
		this.quantidadeProduto = quantidadeProduto;
		this.gatewayDePagamento = gatewayDePagamento;
	}

	public Long getId() {
		return id;
	}

	public String getProduto_Nome() {
		return produto.getNome();
	}

	public String getProduto_EmailDono() {
		return produto.getDono().getUsername();
	}

	public Long getProduto_IdDono() {
		return produto.getDono().getId();
	}

	public String getPedido_EmailUsuario() {
		return comprador.getUsername();
	}

	public Long getPedido_IdUsuario() {
		return comprador.getId();
	}

	public GatewayDePagamento getGatewayDePagamento() {
		return gatewayDePagamento;
	}

	public BigDecimal getValorTotal() {
		return valorProduto.multiply(BigDecimal.valueOf(quantidadeProduto));
	}

	public Integer getQuantidadeProduto() {
		return quantidadeProduto;
	}

	public String getUrlGatewayDePagamento() {
		return this.gatewayDePagamento.gerarUrlDoGateway(this);
	}

	private void setStatus(Status status) {
		this.status = status;
	}

	public void addTransacao(Transacao transacao) {
		if (transacao.getStatus()) {
			this.setStatus(Status.PAGAMENTO_EFETUADO);
		}
		this.transacoes.add(transacao);
	}

	public Status getStatus() {
		return status;
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", produto=" + produto + ", comprador=" + comprador + ", valorProduto="
				+ valorProduto + ", quantidadeProduto=" + quantidadeProduto + ", formaDePagamento=" + gatewayDePagamento
				+ ", status=" + status + "]";
	}

}
