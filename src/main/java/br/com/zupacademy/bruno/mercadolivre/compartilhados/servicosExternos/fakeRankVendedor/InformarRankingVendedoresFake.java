package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeRankVendedor;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeNotaFiscal.NotaFiscalLocalApiClient;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InformarRankingVendedoresFake {

    @Autowired
    private RankingVendedoresLocalApiClient rankingApiCLient;

    public void processar(Pedido pedido) {
        rankingApiCLient.informarRanking(pedido.getProduto_IdDono(), pedido.getId());
    }
}
