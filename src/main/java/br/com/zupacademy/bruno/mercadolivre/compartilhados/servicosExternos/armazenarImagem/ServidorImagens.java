package br.com.zupacademy.bruno.mercadolivre.compartilhados.servicosExternos.armazenarImagem;

import java.util.Set;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.ImagensRequest;

public interface ServidorImagens {

	public Set<String> upload(ImagensRequest listaImagens);
}
