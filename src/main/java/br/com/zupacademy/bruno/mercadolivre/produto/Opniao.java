package br.com.zupacademy.bruno.mercadolivre.produto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

@Entity
public class Opniao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Min(value = 1)
	@Max(value = 5)
	private Integer nota;

	@NotBlank
	@Size(max = 50)
	private String titulo;

	@NotBlank
	@Size(max = 500)
	private String descricao;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;

	public Opniao() {
		super();
	}

	public Opniao(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank @Size(max = 50) String titulo,
			@NotBlank @Size(max = 500) String descricao, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
		this.usuario = usuario;
		this.produto = produto;
	}

}
