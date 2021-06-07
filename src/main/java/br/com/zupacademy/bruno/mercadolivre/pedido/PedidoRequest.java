//package br.com.zupacademy.bruno.mercadolivre.pedido;
//
//import javax.validation.constraints.Min;
//import javax.validation.constraints.NotNull;
//
//import org.springframework.util.Assert;
//
//import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
//import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
//import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.ValueOfEnum;
//
//public class PedidoRequest {
//
////	@NotNull
////	@Min(value = 1)
////	private Integer quantidade;
////
////	@NotNull
////	@ValueOfEnum(enumClass = FormaDePagamento.class)
////	private String formaDePagamento;
////
////	public PedidoRequest(@Min(1) Integer quantidade, @NotNull String formaDePagamento) {
////		super();
////		this.quantidade = quantidade;
////		this.formaDePagamento = formaDePagamento;
////	}
////
////	public Pedido toModel(Produto produto, Usuario comprador) {
////		FormaDePagamento formaDePagamentoEnum = FormaDePagamento.valueOf(formaDePagamento);
////		
////		if(isValidoQuantidade(produto)) {
////			Assert.state(!isValidoQuantidade(produto), "Estoque insuficiente para esta quantidade");
////		}
////		
////		return new Pedido(produto, comprador, this.quantidade, formaDePagamentoEnum);
////	}
////	
////	private boolean isValidoQuantidade(Produto produto) {
////		return produto.getQuantidadeDisponivel() - this.quantidade < 0;
////	}
//
//}
