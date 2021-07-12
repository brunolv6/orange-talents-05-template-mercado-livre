package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.enviarEmail.fluxoCompra.FakeEnviarEmailCompra;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;

public enum StatusTransacao {
	SUCESSO,
	FALHA
}
