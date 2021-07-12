package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.fakeRotasDePagamento;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class fakePagSeguroService {

	@GetMapping("/pagseguro.com")
	public String pagar(@RequestParam(name = "buyerId") String pedidoId, @RequestParam(name = "redirectUrl") String redirectUrl) {
		return "pedido id: " + pedidoId + " e redirectUrl após pagamento: " + redirectUrl; 
	}
}