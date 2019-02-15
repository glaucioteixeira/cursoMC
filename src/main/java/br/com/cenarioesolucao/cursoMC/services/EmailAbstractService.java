package br.com.cenarioesolucao.cursoMC.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import br.com.cenarioesolucao.cursoMC.domains.Pedido;

/**
 * Padrão de projeto Template Method
 * @author glaucio
 *
 */
public abstract class EmailAbstractService implements EmailService {

	@Value("${default.sender}")
	private String from;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	
	@Override
	public void emailConfirmacaoPedido(Pedido pedido) {
		SimpleMailMessage message = prepareSmmFromPedido(pedido);
		enviaEmail(message);
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
	
	
	@Override
	public void emailConfirmacaoPedidoHtml(Pedido pedido) {
		MimeMessage message;
		try {
			message = prepareMmFromPedido(pedido);
			enviaEmailHtml(message);
		} catch (MessagingException e) {
			emailConfirmacaoPedido(pedido);
		}
		
	}
	
	protected MimeMessage prepareMmFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mm = javaMailSender.createMimeMessage();
		MimeMessageHelper mmHelper = new MimeMessageHelper(mm, true);
		
		mmHelper.setTo(pedido.getCliente().getEmail());
		mmHelper.setFrom(this.from);
		mmHelper.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mmHelper.setSentDate(new Date(System.currentTimeMillis()));
		mmHelper.setText(htmlFromTemplateConfirmacaoPedido(pedido), true);
		
		return mm;
	}

	protected String htmlFromTemplateConfirmacaoPedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		
		return templateEngine.process("email/confirmacaoPedido", context);
	}
}
