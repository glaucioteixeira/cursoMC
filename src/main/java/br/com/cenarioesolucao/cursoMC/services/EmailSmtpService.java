package br.com.cenarioesolucao.cursoMC.services;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class EmailSmtpService extends EmailAbstractService {

	@Autowired
	private MailSender mailSender; // Texto plano
	
	@Autowired
	private JavaMailSender javaMailSender; // Texto HTML
	
	/**
	 * Atributos
	 */
	private static final Logger LOG = LoggerFactory.getLogger(EmailSmtpService.class);
	
	
	@Override
	public void enviaEmail(SimpleMailMessage message) {
		LOG.info("Iniciando envio de email ...");
		this.mailSender.send(message);
		LOG.info("Email enviado!");
		
	}

	@Override
	public void enviaEmailHtml(MimeMessage message) {
		LOG.info("Iniciando envio de email HTML ...");
		this.javaMailSender.send(message);
		LOG.info("Email HTML enviado!");
		
	}

}
