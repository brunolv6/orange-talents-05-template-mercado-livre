package br.com.zupacademy.bruno.mercadolivre.pedido;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.email.FakeEnviarEmailCompra;

@RestController
@RequestMapping("/api/{id}/pedidos")
public class PedidoController {
	
	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FakeEnviarEmailCompra fakeEnviarEmailCompra;

	@PostMapping
	@Transactional
	public ResponseEntity<?> pedir(@RequestBody @Valid PedidoRequest pedidoRequest, @PathVariable Long id, @AuthenticationPrincipal Usuario comprador) {
		
		Produto produto = em.find(Produto.class, id);
		
		Pedido novoPedido = pedidoRequest.toModel(produto, comprador);
		
		em.persist(novoPedido);
		
		fakeEnviarEmailCompra.emailPedidoIniciado(novoPedido);
		
		HttpHeaders headers = new HttpHeaders();
		
		if(novoPedido.getGatewayDePagamento() == GatewayDePagamento.PAYPAL) {
			headers.add("Location", "https://paypal.com?buyerId=" + novoPedido.getIdPedido() + "&redirectUrl=http://localhost:8080/api/retorno-paypal/" + novoPedido.getIdPedido());
		} else {
			headers.add("Location", "https://pagseguro.com?buyerId=" + novoPedido.getIdPedido() + "&redirectUrl=http://localhost:8080/api/retorno-pagseguro/" + novoPedido.getIdPedido());
		}

		// new ResponseEntity<byte []>(null, headers, HttpStatus.FOUND)

		return ResponseEntity.status(HttpStatus.FOUND).build();
	}
}
