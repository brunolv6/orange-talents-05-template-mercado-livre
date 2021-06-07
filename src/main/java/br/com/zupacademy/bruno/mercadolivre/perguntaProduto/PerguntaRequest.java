package br.com.zupacademy.bruno.mercadolivre.perguntaProduto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.Produto;
import br.com.zupacademy.bruno.mercadolivre.cadastroUsuario.Usuario;

public class PerguntaRequest {

	@NotBlank
	@Size(max = 50)
	private String titulo;

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTitulo() {
		return titulo;
	}

	public Pergunta toModel(Usuario usuario, Produto produto) {
		// TODO Auto-generated method stub
		return new Pergunta(this.titulo, usuario, produto);
	}

}
