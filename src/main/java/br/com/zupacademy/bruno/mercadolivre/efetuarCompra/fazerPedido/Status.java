package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.enviarEmail.fluxoCompra.FakeEnviarEmailCompra;

public enum Status {

	INICIADA{
		
		@Override
		public void enviarEmailAviso(Pedido pedido, FakeEnviarEmailCompra fakeEnviarEmailCompra) {
			System.out.println(pedido.toString());
			fakeEnviarEmailCompra.emailPedidoIniciado(pedido);
		}
	},
	PAGAMENTO_EFETUADO{

		@Override
		public void enviarEmailAviso(Pedido pedido, FakeEnviarEmailCompra fakeEnviarEmailCompra) {
			System.out.println(pedido.toString());
			fakeEnviarEmailCompra.emailPedidoPagamentoEfetuado(pedido);
		}
	};

	public abstract void enviarEmailAviso(Pedido pedido, FakeEnviarEmailCompra fakeEnviarEmailCompra);
}
