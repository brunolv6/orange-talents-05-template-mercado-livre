package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeRankVendedor;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url="http://localhost:8080/api/local", name = "rankvendedores")
public interface RankingVendedoresLocalApiClient {

    @PostMapping("/rankingvendedores/{idVendedor}")
    void informarRanking(@PathVariable("idVendedor") Long idVendedor, @RequestBody Long idCompra);
}
