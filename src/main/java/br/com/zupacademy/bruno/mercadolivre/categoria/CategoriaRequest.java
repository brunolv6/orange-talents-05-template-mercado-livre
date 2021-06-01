package br.com.zupacademy.bruno.mercadolivre.categoria;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;

import org.springframework.util.Assert;

import br.com.zupacademy.bruno.mercadolivre.validator.Unico;

public class CategoriaRequest {

	@NotBlank
	@Unico(entidade = Categoria.class, atributo = "nome")
	private String nome;

	private Long idCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	@Override
	public String toString() {
		return "CategoriaRequest [nome=" + nome + ", idCategoriaMae=" + idCategoriaMae + "]";
	}

	public Categoria toModel(EntityManager em) {

		Categoria categoria = new Categoria(this.nome);

		if (this.idCategoriaMae != null) {
			Categoria categoriaMae = em.find(Categoria.class, this.idCategoriaMae);

			Assert.notNull(categoriaMae, "Categoria mãe não encontrada");

			categoria.setCategoriaMae(categoriaMae);
		}

		return categoria;
	}

}
