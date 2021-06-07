package br.com.zupacademy.bruno.mercadolivre.compartilhados.email;

import br.com.zupacademy.bruno.mercadolivre.perguntaProduto.Pergunta;

public interface EmailInterface {
	
	public void send(Pergunta pergunta);
}
