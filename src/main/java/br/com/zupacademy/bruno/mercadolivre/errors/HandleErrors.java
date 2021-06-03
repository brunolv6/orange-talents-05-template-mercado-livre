package br.com.zupacademy.bruno.mercadolivre.errors;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.AbstractNestablePropertyAccessor;
import org.springframework.beans.NotReadablePropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandleErrors {

	@Autowired
	private MessageSource messageSource;

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<ErrosFormularioDto> handle(MethodArgumentNotValidException exception) {

		List<ErrosFormularioDto> listaErros = new ArrayList<>();

		List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

		fieldErrors.forEach(e -> {
			String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
			ErrosFormularioDto erro = new ErrosFormularioDto(e.getField(), mensagem);
			listaErros.add(erro);
		});

		return listaErros;
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity handleException(IllegalArgumentException e) {
		ErrosFormularioDto erroDto = new ErrosFormularioDto("banco de dados", e.getMessage());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
	}
	
	@ExceptionHandler(InternalAuthenticationServiceException.class)
	public ResponseEntity handleAuthenticationException(InternalAuthenticationServiceException e) {
		ErrosFormularioDto erroDto = new ErrosFormularioDto("Dados de acesso", "incorretos ou não encontrados");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
	}
	
	@ExceptionHandler(NotReadablePropertyException.class)
	public ResponseEntity handleAuthenticationException2(NotReadablePropertyException e) {
		ErrosFormularioDto erroDto = new ErrosFormularioDto("Características", "todas devem ter nome e descrição");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroDto);
	}

}
