package br.com.zupacademy.bruno.mercadolivre.compartilhados.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ValueOfEnumValidator.class)
public @interface ValueOfEnum {
	
    Class<? extends Enum<?>> enumClass();
    
    String message() default "Não existe esta forma de pagamento";
    
    Class<?>[] groups() default {};
    
    Class<? extends Payload>[] payload() default {};
}
