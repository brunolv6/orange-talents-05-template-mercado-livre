package br.com.zupacademy.bruno.mercadolivre.compartilhados.validators;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValueOfEnumValidator implements ConstraintValidator<ValueOfEnum, String> {
	
    private List<String> acceptedValues;

    @Override
    public void initialize(ValueOfEnum annotation) {
      	System.out.println("1");
        acceptedValues = Stream.of(annotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
      	System.out.println("2");
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    	System.out.println("3");
        if (value == null) {
            return true;
        }
      	System.out.println("4");

        return acceptedValues.contains(value.toString());
    }
}