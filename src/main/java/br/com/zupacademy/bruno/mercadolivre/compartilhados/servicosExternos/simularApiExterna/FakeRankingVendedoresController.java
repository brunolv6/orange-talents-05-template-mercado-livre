package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.simularApiExterna;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/local")
public class FakeRankingVendedoresController {

    @PostMapping("/rankingvendedores/{idVendedor}")
    public void gerarNota(@PathVariable("idVendedor") Long idVendedor, @RequestBody Long idCompra){
        System.out.println("Dados da compra " + idCompra + " sendo processado no ranking do vendedor de id " + idVendedor);
    }
}
