package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPagSeguro;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.ProcessarTransacao;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.TransacaoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping
public class TransacaoPagSeguroController {

    @Autowired
    private ProcessarTransacao processarTransacao;

    @PostMapping("/api/retorno-pagseguro/{idPedido}")
    @Transactional
    public ResponseEntity<?> receber(@PathVariable Long idPedido, @RequestBody @Valid TransacaoRequestPagSeguro transacaoRequestPagseguro){

        TransacaoRequest transacaoRequest = transacaoRequestPagseguro.toModel();

        return processarTransacao.processar(transacaoRequest, idPedido);

    }
}
