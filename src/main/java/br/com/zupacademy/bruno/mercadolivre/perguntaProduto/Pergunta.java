package br.com.zupacademy.bruno.mercadolivre.perguntaProduto;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;

@Entity
public class Pergunta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String titulo;

	@NotNull
	@ManyToOne
	private Usuario usuario;

	@NotNull
	@ManyToOne
	private Produto produto;
	
	@PastOrPresent
	private LocalDateTime instanteCriacao = LocalDateTime.now();

	@Deprecated
	public Pergunta() {
		super();
	}

	public Pergunta(@NotBlank String titulo, @NotNull Usuario usuario, @NotNull Produto produto) {
		super();
		this.titulo = titulo;
		this.usuario = usuario;
		this.produto = produto;
	}

	public String getTitulo() {
		return titulo;
	}

	public String getProduto_Dono_Email() {
		return produto.getDono().getUsername();
	}

	public String getProduto_Nome() {
		return produto.getNome();
	}
}
