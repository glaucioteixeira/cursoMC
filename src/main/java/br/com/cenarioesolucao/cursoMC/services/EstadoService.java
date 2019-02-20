package br.com.cenarioesolucao.cursoMC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Estado;
import br.com.cenarioesolucao.cursoMC.repositories.EstadoRepository;

@Service
public class EstadoService {
	
	@Autowired
	private EstadoRepository estadoRepo;
	
	public List<Estado> buscarTodas() {
		return estadoRepo.findAllByOrderByNome();
	}

}
