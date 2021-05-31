package br.com.zupacademy.bruno.mercadolivre.usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class UsuarioRequest {

	@NotBlank
	@Email
	private String email;

	@NotBlank
	@Length(min = 6)
	private String senha;

	public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha) {
		this.email = email;
		this.senha = senha;
	}

	public Usuario toModel() {
		return new Usuario(this.email, this.senha);
	}

}
