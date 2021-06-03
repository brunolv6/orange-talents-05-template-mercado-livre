package br.com.zupacademy.bruno.mercadolivre.produto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.security.GerenciarToken;
import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {
	
	@Autowired
	private GerenciarToken gerenciarToken;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest, @RequestHeader(name = "Authorization") String token) {
		
		Usuario usuario = getUsuarioByToken(token);
		
		Assert.notNull(usuario, "Usuário não encontrado");
		
		Produto novoProduto = produtoRequest.toModel(em, usuario);
		
		em.persist(novoProduto);
		
		return ResponseEntity.ok().build();
	}
	
	private Usuario getUsuarioByToken(String token) {

		token = token.substring(7, token.length());
		
		Long id = gerenciarToken.getIdUsuario(token);
		
		return em.find(Usuario.class, id);
	}
}
