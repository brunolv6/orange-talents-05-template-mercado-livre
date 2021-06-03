package br.com.zupacademy.bruno.mercadolivre.produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.categoria.Categoria;
import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

public class ProdutoRequest {

	@NotBlank
	private String nome;

	@Positive
	private BigDecimal valor;

	@PositiveOrZero
	private Integer quantidadeDisponivel;

	@Size(min = 3)
	private List<@Valid CaracteristicasRequest> caracteristicas = new ArrayList<>();

	@NotBlank
	@Length(max = 1000)
	private String descricao;

	@NotNull
	private Long categoriaId;

	public ProdutoRequest(@NotBlank String nome, @Positive BigDecimal valor,
			@PositiveOrZero Integer quantidadeDisponivel, List<CaracteristicasRequest> caracteristicas,
			@NotBlank @Length(max = 1000) String descricao, @NotNull Long categoriaId) {
		super();
		this.nome = nome;
		this.valor = valor;
		this.quantidadeDisponivel = quantidadeDisponivel;
		this.caracteristicas = caracteristicas;
		this.descricao = descricao;
		this.categoriaId = categoriaId;
	}

	public Produto toModel(EntityManager em, Usuario usuario) {
		Categoria categoria = em.find(Categoria.class, this.categoriaId);
		
		if(categoria == null) {
			Assert.notNull(categoria, "Categoria não encontrada ou inexistente");
		}
		
		Produto produto = new Produto(usuario, this.nome, this.valor, this.quantidadeDisponivel, this.descricao, categoria);
		
		caracteristicas.forEach(caracteristicaRequest -> {
			Caracteristica caracteristica = caracteristicaRequest.toModel();
			
			em.persist(caracteristica);
			
			produto.setCaracteristicas(caracteristica);
		});
		
		return produto;
	}

}
