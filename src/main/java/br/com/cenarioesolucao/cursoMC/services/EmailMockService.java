package br.com.cenarioesolucao.cursoMC.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class EmailMockService extends EmailAbstractService {

	private static final Logger LOG = LoggerFactory.getLogger(EmailMockService.class);
	
	@Override
	public void enviaEmail(SimpleMailMessage message) {
		LOG.info("Simulando envio de email ...");
		LOG.info(message.toString());
		LOG.info("Email enviado!");
		
	}

	@Override
	public void enviaEmailHtml(MimeMessage message) {
		LOG.info("Simulando envio de email HTML ...");
		LOG.info(message.toString());
		LOG.info("Email HTML enviado!");
		
	}

}
