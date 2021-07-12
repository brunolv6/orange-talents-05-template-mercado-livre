package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeNotaFiscal;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://localhost:8080/api/local", name = "notafiscal")
public interface NotaFiscalLocalApiClient {

    @PostMapping("/notasfiscais/{idCompra}")
    void gerarNota(@PathVariable("idCompra") Long idCompra, @RequestBody Long idUsuario);
}

