package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPagSeguro;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.UnicoESucesso;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.Transacao;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.TransacaoRequest;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class TransacaoRequestPagSeguro {

    @NotNull
    @UnicoESucesso(entidade = Transacao.class, atributo = "idTransacao")
    private String idTransacao;

    @NotNull
    @NotBlank
    private String status;

    public TransacaoRequestPagSeguro(@NotNull String idTransacao, @NotNull @NotBlank String status) {
        super();
        this.idTransacao = idTransacao;
        this.status = status;
    }

    @Override
    public String toString() {
        return "TransacaoRequest [idTransacao=" + idTransacao + ", status=" + status + "]";
    }
    
    public TransacaoRequest toModel() {
        Boolean statusBoolean = (status == "SUCESSO");
        return new TransacaoRequest(idTransacao, statusBoolean);
    }
}
