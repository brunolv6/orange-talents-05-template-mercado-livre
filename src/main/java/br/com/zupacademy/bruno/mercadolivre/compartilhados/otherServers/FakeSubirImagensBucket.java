package br.com.zupacademy.bruno.mercadolivre.compartilhados.otherServers;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.cadastroProduto.ImagensRequest;

@Component
public class FakeSubirImagensBucket implements ExternalServer{

	@Override
	public Set<String> upload(ImagensRequest listaImagens){
		Set<String> links = listaImagens.getImagens()
										.stream()
										.map(imagem -> "http://www.bucket.com/brunolv/" + imagem.getOriginalFilename())
										.collect(Collectors.toSet());
		return links;
	}	
	
}
