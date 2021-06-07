package br.com.zupacademy.bruno.mercadolivre.cadastroProduto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.bruno.mercadolivre.cadastroCategoria.Categoria;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;

@Entity
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@NotNull
	private Usuario dono;

	@NotBlank
	private String nome;

	@Positive
	private BigDecimal valor;

	@PositiveOrZero
	private Integer quantidadeDisponivel;

	@Size(min = 3)
	@ManyToMany
	private List<Caracteristica> caracteristicas = new ArrayList<>();

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@ManyToOne
	@NotNull
	private Categoria categoria;

	@NotNull
	private LocalDateTime dataCriacao = LocalDateTime.now();

	@OneToMany(cascade = CascadeType.ALL) // cascade works for persist objects like Image before they've been persisted manually
	private Set<Imagem> urlImagens = new HashSet<Imagem>();

	@Deprecated
	public Produto() {
	}

	public Produto(@NotNull Usuario dono, @NotBlank String nome, @Positive BigDecimal valor,
			@PositiveOrZero Integer quantidadeDisponivel, @NotBlank @Length(max = 1000) String descricao,
			Categoria categoria) {
		super();
		this.dono = dono;
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.descricao = descricao;
		this.categoria = categoria;
	}

	public void setCaracteristicas(Caracteristica caracteristica) {
		this.caracteristicas.add(caracteristica);
	}

	public void setUrlImagem(Imagem urlImagem) {
		this.urlImagens.add(urlImagem);
	}

	public Long getId() {
		return id;
	}

	public Usuario getDono() {
		return dono;
	}

	public String getNome() {
		return nome;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public Integer getQuantidadeDisponivel() {
		return quantidadeDisponivel;
	}

	public List<Caracteristica> getCaracteristicas() {
		return caracteristicas;
	}

	public String getDescricao() {
		return descricao;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}

	@Override
	public String toString() {
		return "Produto [id=" + id + ", dono=" + dono + ", nome=" + nome + ", valor=" + valor
				+ ", quantidadeDisponivel=" + quantidadeDisponivel + ", caracteristicas=" + caracteristicas
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dataCriacao=" + dataCriacao
				+ ", urlImagens=" + urlImagens + "]";
	}

}
