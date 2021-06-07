package br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers;

import java.util.Set;

import br.com.zupacademy.bruno.mercadolivre.produto.ImagensRequest;

public interface ExternServer {

	public Set<String> upload(ImagensRequest listaImagens);
}
