package br.com.cenarioesolucao.cursoMC.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import br.com.cenarioesolucao.cursoMC.domains.Pedido;

/**
 * Padrão de projeto Template Method
 * @author glaucio
 *
 */
public abstract class EmailAbstractService implements EmailService {

	@Value("${default.sender}")
	private String from;
	
	@Override
	public void emailConfirmacaoPedido(Pedido pedido) {
		SimpleMailMessage smm = prepareSmmFromPedido(pedido);
		enviaEmail(smm);
	}

	protected SimpleMailMessage prepareSmmFromPedido(Pedido pedido) {
		SimpleMailMessage smm = new SimpleMailMessage();
		
		smm.setTo(pedido.getCliente().getEmail());
		smm.setFrom(this.from);
		smm.setSubject("Pedido confirmado! Código: " + pedido.getId());
		smm.setSentDate(new Date(System.currentTimeMillis()));
		smm.setText(pedido.toString());
		
		return smm;
	}
}
