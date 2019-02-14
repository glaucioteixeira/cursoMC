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

import br.com.cenarioesolucao.cursoMC.DTO.ClienteDTO;
import br.com.cenarioesolucao.cursoMC.DTO.ClienteNewDTO;
import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> buscarId(@PathVariable Integer id) {
		
		Cliente body = service.buscarId(id);
		
		return ResponseEntity.ok().body(body);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> guardarEntidade(@Valid @RequestBody ClienteNewDTO entityDTO) {
		Cliente entity = service.fromDTO(entityDTO); // Convertendo de DTO para objeto
		
		entity = service.guardarEntidade(entity);
		
		/* Preparando para devolver a URI se o status for 201 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(entity.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarEntidade(@Valid @RequestBody ClienteDTO entityDTO, @PathVariable Integer id) {
		Cliente entity = service.fromDTO(entityDTO); // Convertendo de DTO para objeto
		
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
	public ResponseEntity<List<ClienteDTO>> buscarTodas() {
		List<Cliente> body = service.buscarTodas();
		
		List<ClienteDTO> bodyDTO = body.stream().map(categoria -> new ClienteDTO(categoria)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(bodyDTO);
	}
	
	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> buscarPaginado(@RequestParam(value = "page", defaultValue = "0") Integer page, 
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage, 
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, 
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {
		
		Page<Cliente> body = service.buscarPaginado(page, linesPerPage, orderBy, direction);
		
		Page<ClienteDTO> bodyDTO = body.map(categoria -> new ClienteDTO(categoria));
		
		return ResponseEntity.ok().body(bodyDTO);
	}
}
