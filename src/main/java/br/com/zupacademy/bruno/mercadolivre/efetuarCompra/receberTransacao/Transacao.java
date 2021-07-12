package br.com.zupacademy.bruno.mercadolivre.efetuarCompra.receberTransacao;

import br.com.zupacademy.bruno.mercadolivre.efetuarCompra.fazerPedido.Pedido;

import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;

@Entity
public class Transacao {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String idTransacao;

	@NotNull
	@ManyToOne(cascade = CascadeType.ALL)
	private Pedido pedido;

	@NotNull
	@PastOrPresent
	private LocalDateTime instante = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

	@NotNull
	@Enumerated(EnumType.STRING)
	private StatusTransacao status;

	@Deprecated
	public Transacao() {
		super();
	}

	public Transacao(@NotNull String idTransacao, @NotNull Pedido pedido, StatusTransacao status) {
		super();
		this.idTransacao = idTransacao;
		this.pedido = pedido;
		this.status = status;
	}

	public Boolean getStatus() {
		return status == StatusTransacao.SUCESSO;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idTransacao == null) ? 0 : idTransacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Transacao other = (Transacao) obj;
		if (idTransacao == null) {
			if (other.idTransacao != null)
				return false;
		} else if (!idTransacao.equals(other.idTransacao))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Transacao [id=" + id + ", idTransacao=" + idTransacao + ", pedido=" + pedido + ", instante=" + instante
				+ ", status=" + status + "]";
	}

}
