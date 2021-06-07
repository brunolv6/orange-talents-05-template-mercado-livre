package br.com.zupacademy.bruno.mercadolivre.compartilhados.errors;

public class ErrosFormularioDto {

	private String campo;

	private String erro;

	public ErrosFormularioDto(String campo, String erro) {
		super();
		this.campo = campo;
		this.erro = erro;
	}
	
	public String getCampo() {
		return campo;
	}

	public String getErro() {
		return erro;
	}

}
