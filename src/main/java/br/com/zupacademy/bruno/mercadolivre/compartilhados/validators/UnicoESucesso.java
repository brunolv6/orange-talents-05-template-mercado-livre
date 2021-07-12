package br.com.zupacademy.bruno.mercadolivre.compartilhados.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ FIELD, CONSTRUCTOR })
@Retention(RUNTIME)
@Constraint(validatedBy = { UnicoESucessoValidator.class })
public @interface UnicoESucesso {
	String message() default "Já existe esta transação de forma bem sucedida";
	 
	Class<?>[] groups() default {}; 
 
	Class<? extends Payload>[] payload() default {}; 
	
	Class<?> entidade();
	
	String atributo();
}
