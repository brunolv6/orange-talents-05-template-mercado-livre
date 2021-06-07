package br.com.zupacademy.bruno.mercadolivre.compartilhados.configs.security;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private GerenciarToken gerarToken;

	@PostMapping
	public ResponseEntity<TokenResponse> auth(@RequestBody @Valid AuthRequest authRequest) {
		
		UsernamePasswordAuthenticationToken dadosAcesso = authRequest.converter();
		
		Authentication authentication = authManager.authenticate(dadosAcesso);
		
		String token = gerarToken.criar(authentication);

		return ResponseEntity.ok(new TokenResponse(token, "Bearer"));
	}
	
}
