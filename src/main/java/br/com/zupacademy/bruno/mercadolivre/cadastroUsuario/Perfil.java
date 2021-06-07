package br.com.zupacademy.bruno.mercadolivre.cadastroUsuario;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;

@Entity
public class Perfil implements GrantedAuthority{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String nome;

	@Deprecated
	public Perfil() {
	}

	public Perfil(@NotBlank String nome) {
		super();
		this.nome = nome;
	}

	@Override
	public String getAuthority() {
		return this.nome;
	}

}
