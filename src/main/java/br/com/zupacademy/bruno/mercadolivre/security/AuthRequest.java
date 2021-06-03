package br.com.zupacademy.bruno.mercadolivre.security;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class AuthRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	private String senha;

	public AuthRequest(@NotBlank @Email String email, @NotBlank String senha) {
		super();
		this.email = email;
		this.senha = senha;
	}

	public UsernamePasswordAuthenticationToken converter() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}

}
