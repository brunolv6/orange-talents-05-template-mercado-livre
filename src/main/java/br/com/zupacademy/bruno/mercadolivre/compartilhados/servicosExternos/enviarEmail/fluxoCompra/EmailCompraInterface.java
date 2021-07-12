package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.enviarEmail.fluxoCompra;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;

public interface EmailCompraInterface {

	public void emailPedidoIniciado(Pedido pedido);

	public void emailPedidoPagamentoEfetuado(Pedido pedido);

	public void emailPedidoPagamentoNaoEfetuado(Pedido pedido);
}
