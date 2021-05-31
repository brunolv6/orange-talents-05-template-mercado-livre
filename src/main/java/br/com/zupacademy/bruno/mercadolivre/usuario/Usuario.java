package br.com.zupacademy.bruno.mercadolivre.usuario;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.sun.istack.NotNull;

@Entity
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Email
	@Column(unique = true)
	private String email;

	@NotBlank
	@Length(min = 6)
	private String senha;

	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();

	public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senhaRecebida) {
		this.email = email;
		this.senha = hashSenha(senhaRecebida);
	}

	private String hashSenha(String senhaRecebida) {
		Assert.hasLength(senhaRecebida, "Senha não pode ser nulo");
		Assert.isTrue(senhaRecebida.length() >= 6, "Senha deve ter no mínimo 6 caracteres");
		return new BCryptPasswordEncoder().encode(senhaRecebida);
	}

}
