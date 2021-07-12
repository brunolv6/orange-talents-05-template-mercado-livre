package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.enviarEmail.fluxoCompra.FakeEnviarEmailCompra;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeNotaFiscal.GerarNotaFiscalFake;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeRankVendedor.InformarRankingVendedoresFake;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EventosCompra {

    @Autowired
    private FakeEnviarEmailCompra fakeEnviarEmailCompra;

    @Autowired
    private GerarNotaFiscalFake gerarNotaFiscal;

    @Autowired
    private InformarRankingVendedoresFake informarRanking;

    @Autowired
    private FakeEnviarEmailCompra enviarEmail;

    public void processar(Pedido pedido){
        if(pedido.getStatus() == Status.PAGAMENTO_EFETUADO){
            pedido.getStatus().enviarEmailAviso(pedido, fakeEnviarEmailCompra);
            gerarNotaFiscal.processar(pedido);
            informarRanking.processar(pedido);
        } else {
            enviarEmail.emailPedidoPagamentoNaoEfetuado(pedido);
        }
    }
}
