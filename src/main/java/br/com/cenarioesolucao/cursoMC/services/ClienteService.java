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

import br.com.cenarioesolucao.cursoMC.DTO.ClienteDTO;
import br.com.cenarioesolucao.cursoMC.DTO.ClienteNewDTO;
import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.domains.Endereco;
import br.com.cenarioesolucao.cursoMC.domains.Municipio;
import br.com.cenarioesolucao.cursoMC.domains.enums.TipoCliente;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.repositories.EnderecoRepository;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ViolacaoIntegridadeDadoExcecao;

@Service
public class ClienteService {

	// Declara a dependencia ao objeto repository
	@Autowired
	private ClienteRepository clienteRepo;
	@Autowired
	private EnderecoRepository enderecoRepo;
	
	public Cliente buscarId(Integer id) {
		Optional<Cliente> obj = clienteRepo.findById(id);
		
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoExcecao("Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Cliente.class.getName()));
		
	}
	
	@Transactional
	public Cliente guardarEntidade(Cliente entity) {
		entity.setId(null);
		entity = clienteRepo.save(entity);
		enderecoRepo.saveAll(entity.getEnderecos());
		
		return entity;
	}
	
	@Transactional
	public Cliente atualizarEntidade(Cliente entity) {
		Cliente newEntity = buscarId(entity.getId());
		atualizaNovoVelho(newEntity, entity);
		
		return clienteRepo.save(newEntity);
	}
	
	
	public void removerId(Integer id) {
		buscarId(id);
		
		try {
			clienteRepo.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new ViolacaoIntegridadeDadoExcecao("Não é possível excluir Cliente que possui entidades relacionadas!");
		}
	}
	
	public List<Cliente> buscarTodas() {
		return clienteRepo.findAll();
	}
	
	public Page<Cliente> buscarPaginado(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		
		return clienteRepo.findAll(pageRequest);
	}
	
	/*
	 * Metodo auxiliar para instanciar uma classe de dominio a partir de um DTO, para realizar atualizações
	 */
	public Cliente fromDTO(ClienteDTO objetoDTO) {
		return new Cliente(objetoDTO.getId(), objetoDTO.getNome(), objetoDTO.getEmail(), null, null);
	}
	
	// Sobreescrita de metodo para instanciar uma classe de dominio a partir de um DTO - Novos registros
	public Cliente fromDTO(ClienteNewDTO objetoDTO) {
		Cliente cliente = new Cliente(null, objetoDTO.getNome(), objetoDTO.getEmail(), objetoDTO.getCpfOuCnpj(), 
				TipoCliente.toEnum(objetoDTO.getTipoCliente()));
		Municipio municipio = new Municipio(objetoDTO.getMunicipioId(), null, null);
		Endereco endereco = new Endereco(null, objetoDTO.getLogradouro(), objetoDTO.getNumero(), 
				objetoDTO.getComplemento(), objetoDTO.getBairro(), objetoDTO.getCep(), cliente, municipio);
		
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(objetoDTO.getTelefone1());
		if (objetoDTO.getTelefone2() != null) {
			cliente.getTelefones().add(objetoDTO.getTelefone2());
		}
		if (objetoDTO.getTelefone3() != null) {
			cliente.getTelefones().add(objetoDTO.getTelefone3());
		}
		
		return cliente;
	}
	
	/*
	 * Metodo para manter atualizado os dados que não serão modificados pela entrada no sistema.
	 */
	private void atualizaNovoVelho(Cliente newEntity, Cliente entity) {
		newEntity.setNome(entity.getNome());
		newEntity.setEmail(entity.getEmail());
	}
}
