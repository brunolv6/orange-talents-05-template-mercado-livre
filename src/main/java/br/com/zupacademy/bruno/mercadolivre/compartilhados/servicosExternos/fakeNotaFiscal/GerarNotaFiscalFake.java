package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeNotaFiscal;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GerarNotaFiscalFake{

    @Autowired
    private NotaFiscalLocalApiClient notaApiClient;

    public void processar(Pedido pedido) {
        notaApiClient.gerarNota(pedido.getId(), pedido.getPedido_IdUsuario());
    }
}
