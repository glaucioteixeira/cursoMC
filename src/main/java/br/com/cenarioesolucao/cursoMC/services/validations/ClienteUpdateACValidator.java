package br.com.cenarioesolucao.cursoMC.services.validations;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import br.com.cenarioesolucao.cursoMC.DTO.ClienteDTO;
import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.resources.exceptions.FieldMessage;

//Anotações Customizadas para atualizar registros 

public class ClienteUpdateACValidator implements ConstraintValidator<ClienteUpdateAC, ClienteDTO>{

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteUpdateAC ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteDTO clienteDTO, ConstraintValidatorContext context) {
		
		/**
		 * Recuperar o numero do id passado na URI
		 */
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		List<FieldMessage> listaError = new ArrayList<>();
		
		/* Testa se o email não existe no banco de dados */
		Cliente aux = repo.findByEmail(clienteDTO.getEmail());
		if (aux != null && !aux.getId().equals(uriId)) {
			listaError.add(new FieldMessage("email", "Email já existe!"));
		}
		
		for (FieldMessage e : listaError) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		
		return listaError.isEmpty();
	}

}
