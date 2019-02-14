package br.com.cenarioesolucao.cursoMC.services.validations;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.cenarioesolucao.cursoMC.DTO.ClienteNewDTO;
import br.com.cenarioesolucao.cursoMC.domains.Cliente;
import br.com.cenarioesolucao.cursoMC.domains.enums.TipoCliente;
import br.com.cenarioesolucao.cursoMC.repositories.ClienteRepository;
import br.com.cenarioesolucao.cursoMC.resources.exceptions.FieldMessage;
import br.com.cenarioesolucao.cursoMC.services.validations.utils.BR;

//Anotações Customizadas para novos registros

public class ClienteNewACValidator implements ConstraintValidator<ClienteNewAC, ClienteNewDTO>{

	@Autowired
	private ClienteRepository repo;

	@Override
	public void initialize(ClienteNewAC ann) {
		
	}
	
	@Override
	public boolean isValid(ClienteNewDTO clienteNewDTO, ConstraintValidatorContext context) {
		List<FieldMessage> listaError = new ArrayList<>();
		
		/* Testa se o CPF ou CNPJ é valido */
		if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod()) && !BR.isValidCPF(clienteNewDTO.getCpfOuCnpj())) {
			listaError.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		if (clienteNewDTO.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod()) && !BR.isValidCNPJ(clienteNewDTO.getCpfOuCnpj())) {
			listaError.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		/* Testa se o email não existe no banco de dados */
		Cliente aux = repo.findByEmail(clienteNewDTO.getEmail());
		if (aux != null) {
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
