package br.com.cenarioesolucao.cursoMC.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.cenarioesolucao.cursoMC.DTO.CategoriaDTO;
import br.com.cenarioesolucao.cursoMC.domains.Categoria;
import br.com.cenarioesolucao.cursoMC.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> buscarId(@PathVariable Integer id) {
		Categoria body = service.buscarId(id);
		
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> guardarEntidade(@Valid @RequestBody CategoriaDTO entityDTO) {
		Categoria entity = service.fromDTO(entityDTO); // Convertendo de DTO para objeto
		
		entity = service.guardarEntidade(entity);
		
		/* Preparando para devolver a URI se o status for 201 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarEntidade(@Valid @RequestBody CategoriaDTO entityDTO, @PathVariable Integer id) {
		Categoria entity = service.fromDTO(entityDTO); // Convertendo de DTO para objeto
		
		entity.setId(id);
		entity = service.atualizarEntidade(entity);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removerId(@PathVariable Integer id) {
		service.removerId(id);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> buscarTodas() {
		List<Categoria> body = service.buscarTodas();
		
		List<CategoriaDTO> bodyDTO = body.stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(bodyDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> buscarPaginado(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "descricao") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Categoria> body = service.buscarPaginado(page, linesPerPage, orderBy, direction);
		
		Page<CategoriaDTO> bodyDTO = body.map(categoria -> new CategoriaDTO(categoria));
		
		return ResponseEntity.ok().body(bodyDTO);
	}
}
