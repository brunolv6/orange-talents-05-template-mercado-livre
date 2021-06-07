package br.com.zupacademy.bruno.mercadolivre.cadastroProduto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@Entity
public class Imagem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String linkImagem;

	@Deprecated
	public Imagem() {
		super();
	}

	public Imagem(@NotBlank String linkImagem) {
		super();
		this.linkImagem = linkImagem;
	}

	@Override
	public String toString() {
		return "Imagem [id=" + id + ", linkImagem=" + linkImagem + "]";
	}

}
