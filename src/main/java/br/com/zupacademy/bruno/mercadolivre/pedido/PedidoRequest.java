package br.com.zupacademy.bruno.mercadolivre.pedido;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.ValueOfEnum;

public class PedidoRequest {

	@NotNull
	@Min(value = 1)
	private Integer quantidade;

	@NotNull
	@ValueOfEnum(enumClass = GatewayDePagamento.class)
	private String gatewayDePagamento;

	public PedidoRequest(@Min(1) Integer quantidade, @NotNull String formaDePagamento) {
		super();
		this.quantidade = quantidade;
		this.gatewayDePagamento = formaDePagamento;
	}

	public Pedido toModel(Produto produto, Usuario comprador) {
		GatewayDePagamento gatewayDePagamentoEnum = GatewayDePagamento.valueOf(gatewayDePagamento);
		
		Boolean isValidQuantidade = produto.abaterEstoque(this.quantidade);
		
		Assert.state(isValidQuantidade, "Estoque insuficiente!");
		
		return new Pedido(produto, comprador, this.quantidade, gatewayDePagamentoEnum);
	}

}
