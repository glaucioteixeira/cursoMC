package br.com.cenarioesolucao.cursoMC.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.cenarioesolucao.cursoMC.DTO.EstadoDTO;
import br.com.cenarioesolucao.cursoMC.DTO.MunicipioDTO;
import br.com.cenarioesolucao.cursoMC.domains.Estado;
import br.com.cenarioesolucao.cursoMC.domains.Municipio;
import br.com.cenarioesolucao.cursoMC.services.EstadoService;
import br.com.cenarioesolucao.cursoMC.services.MunicipioService;

@RestController
@RequestMapping("/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private MunicipioService municipioService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> buscarTodas() {
		List<Estado> body = estadoService.buscarTodas();
		
		List<EstadoDTO> bodyDTO = body.stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(bodyDTO);
	}
	
	@RequestMapping(value = "/{estadoId}/municipios", method = RequestMethod.GET)
	public ResponseEntity<List<MunicipioDTO>> procurarMunicipios(@PathVariable Integer estadoId) {
		List<Municipio> body = municipioService.buscarEstado(estadoId);
		
		List<MunicipioDTO> bodyDTO = body.stream().map(municipio -> new MunicipioDTO(municipio)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(bodyDTO);
	}

}
