package br.com.zupacademy.bruno.mercadolivre.cadastroUsuario;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.compartilhados.validators.Unico;

public class UsuarioRequest {

	@NotBlank
	@Email
	@Unico(entidade = Usuario.class, atributo = "email")
	private String email;

	@NotBlank
	@Length(min = 6)
	private String senha;
	
	@Positive
	private Long perfilId;

	public UsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha, Long perfilId) {
		this.email = email;
		this.senha = senha;
		this.perfilId = perfilId;
	}

	public Usuario toModel(EntityManager em) {
		Usuario novoUsuario = new Usuario(this.email, this.senha);
		
		if(perfilId != null) {
			Perfil perfil = em.find(Perfil.class, perfilId);
			Assert.notNull(perfil, "Perfil não existe");
			
			novoUsuario.setPerfis(perfil);
		}
		
		return novoUsuario;
	}

}
