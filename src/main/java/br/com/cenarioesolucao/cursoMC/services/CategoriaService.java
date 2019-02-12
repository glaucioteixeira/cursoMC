package br.com.cenarioesolucao.cursoMC.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class CategoriaService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Categoria.class.getName()));
		
	}
}
