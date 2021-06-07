package br.com.zupacademy.bruno.mercadolivre.compartilhados.validator;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UnicoValidator implements ConstraintValidator<Unico, String> {

	@PersistenceContext
	private EntityManager em;

	private Class<?> entidade;

	private String atributo;

	@Override
	public void initialize(Unico anotacao) {
		this.entidade = anotacao.entidade();
		this.atributo = anotacao.atributo();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Query query = em.createQuery(("select 1 from " + entidade.getName() + " where " + atributo + "= :value"));

		query.setParameter("value", value);

		List<?> resultado = query.getResultList();

		return resultado.isEmpty();
	}

}
