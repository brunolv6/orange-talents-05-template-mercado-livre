package br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers;

import java.util.Set;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.ImagensRequest;

public interface ExternalServer {

	public Set<String> upload(ImagensRequest listaImagens);
}
