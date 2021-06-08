package br.com.zupacademy.bruno.mercadolivre.compartilhados.email;

import org.springframework.stereotype.Component;

import br.com.zupacademy.bruno.mercadolivre.perguntaProduto.Pergunta;

@Component
public class FakeEnviarEmailPergunta implements EmailPerguntaInterface{

	@Override
	public void send(Pergunta pergunta) {
		
		String tituloPergunta = pergunta.getTitulo();
		
		String nomeProduto = pergunta.getProduto_Nome();
		
		String emailDonoProduto = pergunta.getProduto_Dono_Email();
		
		System.out.println("Enviar e-mail para " + emailDonoProduto + ": Você recebeu a seguinte pergunta sobre o produto " + nomeProduto + ": " + tituloPergunta);
	}

}
