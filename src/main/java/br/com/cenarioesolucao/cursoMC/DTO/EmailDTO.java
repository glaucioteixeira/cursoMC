package br.com.cenarioesolucao.cursoMC.DTO;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class EmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	@NotEmpty(message = "Preenchimento obrigatório!")
	@Length(min = 5, max = 80, message = "O tamanho deste campo deve estar entre 5 e 80 caracteres!")
	@Email(message = "Email Inválido!")
	private String email;
	
	/**
	 * Construtores
	 */
	public EmailDTO() {
		
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
