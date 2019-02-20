package br.com.cenarioesolucao.cursoMC.DTO;

import java.io.Serializable;

public class CredencialDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	private String email;
	private String senha;
	
	/**
	 * Construtores
	 */
	public CredencialDTO() {
		
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

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
}
