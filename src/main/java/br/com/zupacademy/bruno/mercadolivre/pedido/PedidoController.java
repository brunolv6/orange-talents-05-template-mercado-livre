//package br.com.zupacademy.bruno.mercadolivre.pedido;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//import javax.transaction.Transactional;
//import javax.validation.Valid;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
//import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
//
//@RestController
//@RequestMapping("/api/{id}/pedidos")
//public class PedidoController {
//	
//	@PersistenceContext
//	private EntityManager em;
//
//	@PostMapping
//	@Transactional
//	public String pedir(@RequestBody @Valid PedidoRequest pedidoRequest, @PathVariable Long id, @AuthenticationPrincipal Usuario comprador) {
//		
//		Produto produto = em.find(Produto.class, id);
//		
//		Pedido novoPedido = pedidoRequest.toModel(produto, comprador);
//		
//		em.persist(novoPedido);
//		
//		return novoPedido.toString();
//	}
//}
