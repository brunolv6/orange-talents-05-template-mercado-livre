package br.com.zupacademy.bruno.mercadolivre.perguntaProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;
import br.com.zupacademy.bruno.mercadolivre.compartilhados.email.FakeEnviarEmail;

@RestController
public class PerguntaController {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	private FakeEnviarEmail fakeEnviarEmail;
	
	@PostMapping("/api/produto/{id}/pergunta")
	@Transactional
	public ResponseEntity<?> perguntar(@RequestBody @Valid PerguntaRequest perguntaRequest, @PathVariable("id") Long id,
			@AuthenticationPrincipal Usuario usuario) {

		Produto produto = em.find(Produto.class, id);

		Assert.notNull(produto, "Produto não encontrado");

		Pergunta novaPergunta = perguntaRequest.toModel(usuario, produto);
		
		produto.addPergunta(novaPergunta);

		em.merge(produto);

		fakeEnviarEmail.send(novaPergunta);

		return ResponseEntity.ok().build();
	}
}
