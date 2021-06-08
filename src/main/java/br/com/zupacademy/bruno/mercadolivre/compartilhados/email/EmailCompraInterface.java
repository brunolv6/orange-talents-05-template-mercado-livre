package br.com.zupacademy.bruno.mercadolivre.compartilhados.email;

import br.com.zupacademy.bruno.mercadolivre.pedido.Pedido;

public interface EmailCompraInterface {

	public void emailPedidoIniciado(Pedido pedido);
}
