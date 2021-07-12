package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPaypal;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.ProcessarTransacao;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.TransacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class TransacaoPaypalController {

    @Autowired
    private ProcessarTransacao processarTransacao;

    @PostMapping("/api/retorno-paypal/{idPedido}")
    @Transactional
    public ResponseEntity<?> receber(@PathVariable Long idPedido, @RequestBody @Valid TransacaoRequestPaypal transacaoRequestPaypal){

        TransacaoRequest transacaoRequest = transacaoRequestPaypal.toModel();

        return processarTransacao.processar(transacaoRequest, idPedido);

    }
}
