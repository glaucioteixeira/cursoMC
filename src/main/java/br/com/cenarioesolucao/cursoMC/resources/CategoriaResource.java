package br.com.cenarioesolucao.cursoMC.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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
	public ResponseEntity<Void> guardarEntidade(@RequestBody Categoria entity) {
		entity = service.guardarEntidade(entity);
		
		/* Preparando para devolver a URI se o status for 201 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarEntidade(@RequestBody Categoria entity, @PathVariable Integer id) {
		entity.setId(id);
		entity = service.atualizarEntidade(entity);
		
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> removerId(@PathVariable Integer id) {
		service.removerId(id);
		
		return ResponseEntity.noContent().build();
	}
}
