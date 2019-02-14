package br.com.cenarioesolucao.cursoMC.services;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.PagamentoComBoleto;

@Service
public class BoletoService {

	public void preencherPagamentoComBoleto(PagamentoComBoleto pagamentoComBoleto, Date instanteDoPedido) {
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(instanteDoPedido);
		calendar.add(Calendar.DAY_OF_MONTH, 7);
		pagamentoComBoleto.setDataVencimento(calendar.getTime());
	}
}
