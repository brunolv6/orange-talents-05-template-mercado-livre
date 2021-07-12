package br.com.zupacademy.bruno.mercadolivre.compartilhados.validators;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.StatusTransacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UnicoESucessoValidator implements ConstraintValidator<UnicoESucesso, String> {

	@PersistenceContext
	private EntityManager em;

	private Class<?> entidade;

	private String atributo;

	@Override
	public void initialize(UnicoESucesso anotacao) {
		this.entidade = anotacao.entidade();
		this.atributo = anotacao.atributo();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {

		Query query = em.createQuery(("select 1 from " + entidade.getName() + " where " + atributo + "= :value and status = : value2"));

		query.setParameter("value", value);

		query.setParameter("value2", StatusTransacao.SUCESSO);

		List<?> resultado = query.getResultList();

		return resultado.isEmpty();
	}

}
