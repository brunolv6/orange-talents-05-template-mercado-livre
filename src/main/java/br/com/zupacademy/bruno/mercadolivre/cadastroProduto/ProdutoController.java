package br.com.zupacademy.bruno.mercadolivre.cadastroProduto;

import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers.FakeSubirImagensBucket;

@RestController
@RequestMapping("/api/produto")
public class ProdutoController {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	private FakeSubirImagensBucket fakeSubirImagensBucket;

	@PostMapping
	@Transactional
	public ResponseEntity<?> cadastrar(@RequestBody @Valid ProdutoRequest produtoRequest,
			@AuthenticationPrincipal Usuario usuario) {

		Produto novoProduto = produtoRequest.toModel(em, usuario);

		em.persist(novoProduto);

		return ResponseEntity.ok().build();
	}

	@PostMapping("/{id}/imagens")
	@Transactional
	public ResponseEntity<?> enviarImagens(@ModelAttribute @Valid ImagensRequest imagensRequest,
			@PathVariable("id") Long id, @AuthenticationPrincipal Usuario usuarioLogado) {

		Produto produto = em.find(Produto.class, id);
		
		Assert.notNull(produto, "Produto não encontrado");

		if (!produto.getDono().getUsername().equals(usuarioLogado.getUsername())) {
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

}
