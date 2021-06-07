package br.com.zupacademy.bruno.mercadolivre.cadastroUsuario;

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
@RequestMapping("/api/usuario")
public class UsuarioController {
	
	@PersistenceContext
	private EntityManager em;

	@PostMapping
	@Transactional
	public ResponseEntity<?> criar(@RequestBody @Valid UsuarioRequest request){
		
		Usuario usuario = request.toModel(em);
		
		em.persist(usuario);
		
		return ResponseEntity.ok().build();
	}
	
}
