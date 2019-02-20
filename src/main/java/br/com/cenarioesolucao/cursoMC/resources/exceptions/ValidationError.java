package br.com.cenarioesolucao.cursoMC.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {

	private static final long serialVersionUID = 1L;

	/**
	 * Atributos
	 */
	private List<FieldMessage> listaError = new ArrayList<>();
	
	
	
	public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}


	/**
	 * Métodos
	 */
	public void addError(String fieldName, String message) {
		listaError.add(new FieldMessage(fieldName, message));
	}
	

	/**
	 * Getters 
	 * @return
	 */
	public List<FieldMessage> getListaError() {
		return listaError;
	}

	

}
