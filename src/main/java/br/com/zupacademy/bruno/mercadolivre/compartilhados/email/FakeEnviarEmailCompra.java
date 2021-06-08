package br.com.zupacademy.bruno.mercadolivre.compartilhados.email;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.pedido.Pedido;

@Component
public class FakeEnviarEmailCompra implements EmailCompraInterface{

	@Override
	public void emailPedidoIniciado(Pedido pedido) {
		String nomeProduto = pedido.getProduto_Nome();
		
		String emailDonoProduto = pedido.getProduto_EmailDono();
		
		System.out.println("Enviar e-mail para " + emailDonoProduto + ": Um usuário deu como INICIADA um processo de compra do produto " + nomeProduto);
	}

}
