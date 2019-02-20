package br.com.cenarioesolucao.cursoMC.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class AuthenticationService {

	@Autowired
	private ClienteRepository clienteRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepo.findByEmail(email);
		
		if (cliente == null) {
			throw new ObjetoNaoEncontradoExcecao("Email não encontrado!");
		}
		
		String novaSenha = newPassword();
		cliente.setSenha(passwordEncoder.encode(novaSenha));
		
		clienteRepo.save(cliente);
		
		emailService.emailNovaSenha(cliente, novaSenha);
		
	}

	private String newPassword() {
		char[] vetorNovaSenha = new char[10];
		
		for (int i = 0; i < 10; i++) {
			vetorNovaSenha[i] = randomChar();
		}
		
		return new String(vetorNovaSenha);
	}

	private char randomChar() {
		int opt = random.nextInt(3);
		
		if (opt == 0) {
			// gera um digito
			return (char) (random.nextInt(10) + 48);
			
		} else if (opt == 1) {
			// gera letra maiuscula
			return (char) (random.nextInt(26) + 65);
			
		} else {
			// gera letra minuscula
			return (char) (random.nextInt(10) + 97);
		}
	}
}
