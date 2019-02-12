package br.com.cenarioesolucao.cursoMC.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class ClienteService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private ClienteRepository repo;
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Cliente.class.getName()));
		
	}
}
