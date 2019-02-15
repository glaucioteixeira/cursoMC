package br.com.cenarioesolucao.cursoMC.services;

import org.springframework.mail.SimpleMailMessage;

import br.com.cenarioesolucao.cursoMC.domains.Pedido;

/**
 * Padrão de projeto Template Method
 * @author glaucio
 *
 */

public interface EmailService {

	void emailConfirmacaoPedido(Pedido pedido);
	
	void enviaEmail(SimpleMailMessage message);
}
