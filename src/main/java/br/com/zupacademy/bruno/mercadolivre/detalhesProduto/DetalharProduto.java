package br.com.zupacademy.bruno.mercadolivre.detalhesProduto;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;

@RestController
@RequestMapping("/api/produto")
public class DetalharProduto {

	@PersistenceContext
	private EntityManager em;

	@GetMapping("/{id}/detalhes")
	@Transactional
	public ResponseEntity<DetalhesProdutoResponse> detalhar(@PathVariable Long id) {
		
		Produto produto = em.find(Produto.class, id);
		
		Assert.notNull(produto, "Produto não encontrado");

		return ResponseEntity.ok(new DetalhesProdutoResponse(produto));
	}
}
