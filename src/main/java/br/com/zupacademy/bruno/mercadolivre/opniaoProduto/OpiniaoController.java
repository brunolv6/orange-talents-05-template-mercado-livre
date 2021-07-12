package br.com.zupacademy.bruno.mercadolivre.opniaoProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;

@RestController
@RequestMapping("/api/produto")
public class OpiniaoController {

	@PersistenceContext
	private EntityManager em;

	@PostMapping("/{id}/opniao")
	@Transactional
	public ResponseEntity<?> opinar(@RequestBody @Valid OpniaoRequest opniaoRequest, @PathVariable("id") Long id,
			@AuthenticationPrincipal Usuario usuario) {

		Produto produto = em.find(Produto.class, id);

		Assert.notNull(produto, "Produto não encontrado");

		Opniao novaOpiniao = opniaoRequest.toModel(usuario, produto);

		produto.addOpniao(novaOpiniao);

		em.merge(produto);

		return ResponseEntity.ok().build();
	}
}
