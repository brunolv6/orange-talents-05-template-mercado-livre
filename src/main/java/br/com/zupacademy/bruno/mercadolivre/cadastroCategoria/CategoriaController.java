package br.com.zupacademy.bruno.mercadolivre.cadastroCategoria;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {

	@PersistenceContext
	private EntityManager em;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?>  criar(@RequestBody @Valid CategoriaRequest categoriaRequest) {
		Categoria novaCategoria = categoriaRequest.toModel(em);
		
		em.persist(novaCategoria);
		
		return ResponseEntity.ok().build();
	}
}
