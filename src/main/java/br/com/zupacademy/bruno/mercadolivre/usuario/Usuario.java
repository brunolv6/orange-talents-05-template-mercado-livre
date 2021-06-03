package br.com.zupacademy.bruno.mercadolivre.usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.Assert;

import com.sun.istack.NotNull;

@Entity
public class Usuario implements UserDetails {

	private static final long serialVersionUID = 1L;

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

	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();

	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@Deprecated
	public Usuario() {
	}

	public Usuario(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senhaRecebida) {
		this.email = email;
		this.senha = hashSenha(senhaRecebida);
	}

	private String hashSenha(String senhaRecebida) {
		Assert.hasLength(senhaRecebida, "Senha não pode ser nulo");
		Assert.isTrue(senhaRecebida.length() >= 6, "Senha deve ter no mínimo 6 caracteres");
		return new BCryptPasswordEncoder().encode(senhaRecebida);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return perfis;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public void setPerfis(Perfil perfil) {
		this.perfis.add(perfil);
	}
	
	public Long getId() {
		return this.id;
	}

}
