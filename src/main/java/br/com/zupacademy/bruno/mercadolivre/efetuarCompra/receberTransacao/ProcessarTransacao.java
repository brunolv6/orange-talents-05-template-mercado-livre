package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPagSeguro.TransacaoRequestPagSeguro;
import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao.viaPaypal.TransacaoRequestPaypal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Component
public class ProcessarTransacao {
	
	@PersistenceContext
	private EntityManager em;

	@Autowired
	private EventosCompra eventosCompra;

	@Transactional
	public ResponseEntity<?> processar(TransacaoRequest transacaoRequest, Long idPedido){

		Pedido pedido = em.find(Pedido.class, idPedido);

		Assert.notNull(pedido, "Pedido não encontrado!");

		Transacao novaTransacao = transacaoRequest.toModel(pedido);

		pedido.addTransacao(novaTransacao);

		em.persist(novaTransacao);
		em.merge(pedido);

		eventosCompra.processar(pedido);

		return ResponseEntity.ok().build();
	}

}
