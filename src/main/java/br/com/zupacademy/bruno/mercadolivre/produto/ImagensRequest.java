package br.com.zupacademy.bruno.mercadolivre.produto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class ImagensRequest {

	@NotNull
	@Size(min = 1)
	private List<MultipartFile> imagens = new ArrayList<>();

	public void setImagens(List<MultipartFile> imagens) {
		this.imagens = imagens;
	}

	public List<MultipartFile> getImagens() {
		return imagens;
	}

	@Override
	public String toString() {
		return "ImagensRequest [imagens=" + imagens.get(0).getOriginalFilename() + " "
				+ imagens.get(1).getOriginalFilename() + "]";
	}

}
