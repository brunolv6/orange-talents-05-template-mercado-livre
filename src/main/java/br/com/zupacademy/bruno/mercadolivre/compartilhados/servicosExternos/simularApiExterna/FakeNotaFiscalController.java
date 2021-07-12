package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.simularApiExterna;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/local")
public class FakeNotaFiscalController {

    @PostMapping("/notasfiscais/{idCompra}")
    public void gerarNota(@PathVariable("idCompra") Long idCompra, @RequestBody Long idUsuario){
        System.out.println("Dados da compra " + idCompra + " do usuário de id " + idUsuario + " sendo processado para geração de nota fiscal");
    }

}
