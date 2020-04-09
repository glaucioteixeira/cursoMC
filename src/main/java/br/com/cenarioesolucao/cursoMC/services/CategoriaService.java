package br.com.cenarioesolucao.cursoMC.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.DTO.CategoriaDTO;
import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ViolacaoIntegridadeDadoExcecao;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CategoriaService {
	
	// Declara a dependencia ao objeto repository
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Categoria buscarId(Integer id) {
		log.info("");
		
		Optional<Categoria> obj = categoriaRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Categoria.class.getName()));
	}
	
	@Transactional
	public Categoria guardarEntidade(Categoria entity) {
		entity.setId(null);
		
		return categoriaRepo.save(entity);
	}
	
	@Transactional
	public Categoria atualizarEntidade(Categoria entity) {
		Categoria newEntity = buscarId(entity.getId());
		atualizaNovoVelho(newEntity, entity);
		
		return categoriaRepo.save(entity);
	}
	
	public void removerId(Integer id) {
		buscarId(id);
		
		try {
			categoriaRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadoExcecao("Não é possível excluir Categoria que possui entidades relacionadas!");
		}
	}
	
	public List<Categoria> buscarTodas() {
		return categoriaRepo.findAll();
	}
	
	public Page<Categoria> buscarPaginado(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return categoriaRepo.findAll(pageRequest);
	}
	
	/*
	 * Metodo auxiliar para instanciar uma classe de dominio a partir de um DTO
	 */
	public Categoria fromDTO(CategoriaDTO objetoDTO) {
		return new Categoria(objetoDTO.getId(), objetoDTO.getDescricao());
	}
	
	/*
	 * Metodo para manter atualizado os dados que não serão modificados pela entrada no sistema.
	 */
	private void atualizaNovoVelho(Categoria newEntity, Categoria entity) {
		newEntity.setDescricao(entity.getDescricao());
	}
}
