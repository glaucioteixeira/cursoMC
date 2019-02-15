package br.com.cenarioesolucao.cursoMC.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import br.com.cenarioesolucao.cursoMC.domains.Pedido;

/**
 * Padrão de projeto Template Method
 * @author glaucio
 *
 */

public interface EmailService {

	/**
	 * Emails
	 */
	void emailConfirmacaoPedido(Pedido pedido);
	void emailConfirmacaoPedidoHtml(Pedido pedido);
	
	/**
	 * Texto Plano
	 * @param message
	 */
	void enviaEmail(SimpleMailMessage message);
	
	/**
	 * Texto HTML
	 * @param message
	 */
	void enviaEmailHtml(MimeMessage message);
}
