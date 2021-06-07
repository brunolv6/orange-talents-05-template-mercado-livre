package br.com.zupacademy.bruno.mercadolivre.testes;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers.FakeSubirImagensBucket;
import br.com.zupacademy.bruno.mercadolivre.usuario.UsuarioRepository;

@RestController
@RequestMapping("/api/teste2")
public class teste2 {

	@Autowired
	private FakeSubirImagensBucket fake;
	
	@GetMapping
	@Transactional
	private String testar() {
		System.out.println(fake);
		return "testado";
	}
	
}
