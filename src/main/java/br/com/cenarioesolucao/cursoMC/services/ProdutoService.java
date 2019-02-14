package br.com.cenarioesolucao.cursoMC.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.domains.Produto;
import br.com.cenarioesolucao.cursoMC.repositories.CategoriaRepository;
import br.com.cenarioesolucao.cursoMC.repositories.ProdutoRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@Service
public class ProdutoService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private ProdutoRepository produtoRepo;
	@Autowired
	private CategoriaRepository categoriaRepo;
	
	public Produto buscarId(Integer id) {
		Optional<Produto> obj = produtoRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> procurarPaginado(String nome, List<Integer> ids, 
			Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepo.findAllById(ids);
				
		return produtoRepo.procurarPaginado(nome, categorias, pageRequest);
		/*
		 * Usando padro de nomes do framework na classe ProdutoRepository
		 * return produtoRepo.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
		 */
		
	}
	
}
