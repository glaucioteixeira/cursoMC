package br.com.cenarioesolucao.cursoMC.domains;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.cenarioesolucao.cursoMC.domains.enums.EstadoPagamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@JsonTypeName("pagamentoComBoleto")
@NoArgsConstructor @ToString 
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Getter @Setter private Date dataVencimento;
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Getter @Setter private Date dataPagamento;
	
	/**
	 * Construtores
	 * @param id
	 * @param estadoPagamento
	 * @param pedido
	 */
	public PagamentoComBoleto(Integer id, EstadoPagamento estadoPagamento, Pedido pedido, 
			Date dataVencimento, Date dataPagamento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
	
	/**
	 * Getters and Setters
	 * @return
	 */
//	public Date getDataVencimento() {
//		return dataVencimento;
//	}
//	public void setDataVencimento(Date dataVencimento) {
//		this.dataVencimento = dataVencimento;
//	}
//	public Date getDataPagamento() {
//		return dataPagamento;
//	}
//	public void setDataPagamento(Date dataPagamento) {
//		this.dataPagamento = dataPagamento;
//	}
	
	
}
