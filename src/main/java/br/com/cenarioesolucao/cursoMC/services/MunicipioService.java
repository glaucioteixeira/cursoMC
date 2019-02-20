package br.com.cenarioesolucao.cursoMC.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.cenarioesolucao.cursoMC.domains.Municipio;
import br.com.cenarioesolucao.cursoMC.repositories.MunicipioRepository;

@Service
public class MunicipioService {
	
	@Autowired
	private MunicipioRepository municipioRepo;
	
	public List<Municipio> buscarEstado(Integer estadoId) {
		return municipioRepo.procurarMunicipios(estadoId);
	}

}
