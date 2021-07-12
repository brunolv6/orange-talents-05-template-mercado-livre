package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao;

import javax.validation.constraints.NotNull;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.Unico;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.UnicoESucesso;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;

public class TransacaoRequest {

	@NotNull
	@UnicoESucesso(entidade = Transacao.class, atributo = "idTransacao")
	private String idTransacao;

	@NotNull
	private Boolean status;

	public TransacaoRequest(@NotNull String idTransacao, @NotNull Boolean status) {
		super();
		this.idTransacao = idTransacao;
		this.status = status;
	}

	@Override
	public String toString() {
		return "TransacaoRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
	}

	public Transacao toModel(Pedido pedido) {
		StatusTransacao statusTransacao = (status)? StatusTransacao.SUCESSO : StatusTransacao.FALHA;
		return new Transacao(idTransacao, pedido, statusTransacao);
	}

}
