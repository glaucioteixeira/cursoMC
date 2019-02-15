package br.com.cenarioesolucao.cursoMC.configurations;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import br.com.cenarioesolucao.cursoMC.services.DatabaseService;
import br.com.cenarioesolucao.cursoMC.services.EmailMockService;
import br.com.cenarioesolucao.cursoMC.services.EmailService;

@Configuration
@Profile("test")
public class ProfileTestConfig {
	/**
	 * Este arquivo é especifico para configurações de um profile (application.properties).
	 * 
	 * Os Beans desta classe somente serão ativados quando o profile estiver ativo no application.properties
	 */
	
	
	@Autowired
	private DatabaseService databaseService;
	
	/**
	 * Metodo :: Responsavel por instanciar a base de dados no profile ativo
	 * @return
	 * @throws ParseException
	 */
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		databaseService.instantiateDatabaseTest();
		
		return true;
	}
	
	/**
	 * Método :: Retorna uma instancia da interface EmailService como EmailMockService
	 * @return
	 */
	@Bean
	public EmailService emailService() {
		return new EmailMockService();
	}

}
