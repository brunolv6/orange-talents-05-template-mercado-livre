package br.com.zupacademy.bruno.mercadolivre.produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import com.sun.istack.NotNull;

import br.com.zupacademy.bruno.mercadolivre.categoria.Categoria;
import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", dono=" + dono + ", nome=" + nome + ", valor=" + valor
				+ ", quantidadeDisponivel=" + quantidadeDisponivel + ", caracteristicas=" + caracteristicas
				+ ", descricao=" + descricao + ", categoria=" + categoria + ", dataCriacao=" + dataCriacao + "]";
	}

}
