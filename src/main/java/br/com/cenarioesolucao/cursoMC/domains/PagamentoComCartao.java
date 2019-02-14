package br.com.cenarioesolucao.cursoMC.domains;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.cenarioesolucao.cursoMC.domains.enums.EstadoPagamento;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
	private static final long serialVersionUID = 1L;

	/**
	 * Atributos
	 */
	private Integer numeroDeParcelas;

	/**
	 * Construtores
	 * @param id
	 * @param estadoPagamento
	 * @param pedido
	 * @param numeroDeParcelas
	 */
	public PagamentoComCartao(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, 
			Integer numeroDeParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroDeParcelas = numeroDeParcelas;
	}
	public PagamentoComCartao() {
		
	}
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public Integer getNumeroDeParcelas() {
		return numeroDeParcelas;
	}
	public void setNumeroDeParcelas(Integer numeroDeParcelas) {
		this.numeroDeParcelas = numeroDeParcelas;
	}
	
	
}
