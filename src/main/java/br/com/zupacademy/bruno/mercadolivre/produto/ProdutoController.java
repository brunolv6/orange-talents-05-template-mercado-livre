package br.com.zupacademy.bruno.mercadolivre.produto;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers.FakeSubirImagensBucket;
import br.com.zupacademy.bruno.mercadolivre.security.GerenciarToken;
import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@Autowired
	private GerenciarToken gerenciarToken;

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private FakeSubirImagensBucket fakeSubirImagensBucket;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,
			@RequestHeader(name = "Authorization") String token) {

		Usuario usuario = getUsuarioByToken(token);

		Produto novoProduto = produtoRequest.toModel(em, usuario);

		em.persist(novoProduto);
		
		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> enviarImagens(@ModelAttribute @Valid ImagensRequest imagensRequest, @PathVariable("id") Long id,
			@RequestHeader(name = "Authorization") String token) {
		
		Produto produto = em.find(Produto.class, id);

		Usuario usuarioLogado = getUsuarioByToken(token);

		if (produto.getDono().getUsername() != usuarioLogado.getUsername()) {
			return ResponseEntity.status(403).build();
		}

		// subir imagens em um servidor e retornar links
		Set<String> linksImagens = fakeSubirImagensBucket.upload(imagensRequest);
	
		Assert.state(linksImagens.size() >= 1, "Deve haver pelo menos 1 imagem");

		// adicionar links no produto
		linksImagens.forEach(linkImagem -> {
			Imagem novaImagem = new Imagem(linkImagem);
			produto.setUrlImagem(novaImagem);
		});

		return ResponseEntity.ok().build();
	}

	private Usuario getUsuarioByToken(String token) {

		token = token.substring(7, token.length());

		Long id = gerenciarToken.getIdUsuario(token);

		Usuario usuario = em.find(Usuario.class, id);

		Assert.notNull(usuario, "Usuário não encontrado");

		return usuario;
	}
}
