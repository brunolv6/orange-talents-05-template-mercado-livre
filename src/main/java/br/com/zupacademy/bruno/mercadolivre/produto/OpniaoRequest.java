package br.com.zupacademy.bruno.mercadolivre.produto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.zupacademy.bruno.mercadolivre.usuario.Usuario;

public class OpniaoRequest {

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

	public OpniaoRequest(@NotNull @Min(1) @Max(5) Integer nota, @NotBlank @Size(max = 50) String titulo,
			@NotBlank @Size(max = 500) String descricao) {
		super();
		this.nota = nota;
		this.titulo = titulo;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "OpniaoRequest [nota=" + nota + ", titulo=" + titulo + ", descricao=" + descricao + "]";
	}

	public Opniao toModel(Usuario usuarioLogado, Produto produto) {
		return new Opniao(nota, titulo, descricao, usuarioLogado, produto);
	}

}
