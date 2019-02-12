package br.com.cenarioesolucao.cursoMC.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.services.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarId(@PathVariable Integer id) {
		
		Cliente body = service.buscar(id);
		
		return ResponseEntity.ok().body(body);
	}
}
