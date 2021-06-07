package br.com.zupacademy.bruno.mercadolivre.compartilhados.validators;

import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ FIELD, CONSTRUCTOR })
@Retention(RUNTIME)
@Constraint(validatedBy = { ExisteValidator.class })
public @interface Existe {
	String message() default "não existe"; 
	 
	Class<?>[] groups() default {}; 
 
	Class<? extends Payload>[] payload() default {}; 
	
	Class<?> entidade();
	
	String atributo();
}
