package br.com.cenarioesolucao.cursoMC.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Pedido;
import br.com.cenarioesolucao.cursoMC.repositories.PedidoRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class PedidoService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private PedidoRepository repo;
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Pedido.class.getName()));
		
	}
}
