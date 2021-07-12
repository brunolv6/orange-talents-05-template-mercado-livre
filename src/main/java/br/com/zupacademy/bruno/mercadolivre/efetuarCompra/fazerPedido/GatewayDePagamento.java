package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido;

public enum GatewayDePagamento {
	PAYPAL {
		@Override
		public String gerarUrlDoGateway(Pedido pedido) {
			return "http://localhost:8080/paypal.com?buyerId=" + pedido.getId() + "&redirectUrl=http://localhost:8080/api/retorno-paypal/" + pedido.getId();
		}
	},
	PAGSEGURO{
		@Override
		public String gerarUrlDoGateway(Pedido pedido) {
			return "http://localhost:8080/pagseguro.com?buyerId=" + pedido.getId() + "&redirectUrl=http://localhost:8080/api/retorno-pagseguro/" + pedido.getId();
		}
	};

	public abstract String gerarUrlDoGateway(Pedido pedido);
}
