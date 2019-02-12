package br.com.cenarioesolucao.cursoMC.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ViolacaoIntegridadeDadoExcecao;

@Service
public class CategoriaService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private CategoriaRepository repo;
	
	public Categoria buscarId(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Categoria.class.getName()));
	}
	
	public Categoria guardarEntidade(Categoria entity) {
		entity.setId(null);
		
		return repo.save(entity);
	}
	
	public Categoria atualizarEntidade(Categoria entity) {
		buscarId(entity.getId());
		
		return repo.save(entity);
	}
	
	public void removerId(Integer id) {
		buscarId(id);
		
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadoExcecao("Não é possível excluir uma Categoria que possui Produtos associados");
		}
	}
}
