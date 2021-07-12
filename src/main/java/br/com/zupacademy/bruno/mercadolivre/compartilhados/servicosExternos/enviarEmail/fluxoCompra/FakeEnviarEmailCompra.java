package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.enviarEmail.fluxoCompra;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;

@Component
public class FakeEnviarEmailCompra implements EmailCompraInterface {

	@Override
	public void emailPedidoIniciado(Pedido pedido) {
		String nomeProduto = pedido.getProduto_Nome();
		
		String emailDonoProduto = pedido.getProduto_EmailDono();
		
		System.out.println("Enviar e-mail para " + emailDonoProduto + ": Um usuário deu como INICIADA um processo de compra do produto " + nomeProduto);
	}

	@Override
	public void emailPedidoPagamentoEfetuado(Pedido pedido) {
		String nomeProduto = pedido.getProduto_Nome();

		String emailUsuarioPedido = pedido.getPedido_EmailUsuario();

		System.out.println(
				"Enviar e-mail para " + emailUsuarioPedido
				+ ":  PAGAMENTO EFETUADO COM SUCESSO do seu pedido de número " + pedido.getId()
				+ " do produto " + nomeProduto
				+ " com quantidade de " + pedido.getQuantidadeProduto()
				+ " e de valor total de R$ " + pedido.getValorTotal()
				+ "."
		);
	}

	@Override
	public void emailPedidoPagamentoNaoEfetuado(Pedido pedido) {
		String nomeProduto = pedido.getProduto_Nome();

		String emailUsuarioPedido = pedido.getPedido_EmailUsuario();

		System.out.println(
				"Enviar e-mail para " + emailUsuarioPedido
						+ ":  PAGAMENTO FALHOU do seu pedido de número " + pedido.getId()
						+ " do produto " + nomeProduto
						+ " com quantidade de " + pedido.getQuantidadeProduto()
						+ " e de valor total de R$ " + pedido.getValorTotal()
						+ ". Segue assim novamente o link de pagamento: " + pedido.getUrlGatewayDePagamento()
		);
	}

}
