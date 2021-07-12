package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPaypal;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.UnicoESucesso;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.Transacao;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.TransacaoRequest;

import javax.validation.constraints.NotNull;

public class TransacaoRequestPaypal {

    @NotNull
    @UnicoESucesso(entidade = Transacao.class, atributo = "idTransacao")
    private String idTransacao;

    @NotNull
    private Boolean status;

    public TransacaoRequestPaypal(@NotNull String idTransacao, @NotNull Boolean status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransacaoRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
    }

    public TransacaoRequest toModel() {
        return new TransacaoRequest(idTransacao, status);
    }

}
