package br.com.cenarioesolucao.cursoMC.resources.exceptions;

import java.io.Serializable;

public class StandardError implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Atributos
	 */
	private Integer status;
	private String message;
	private Long timeStamp;
	
	/**
	 * Construtor
	 * @param status
	 * @param message
	 * @param timeStamp
	 */
	public StandardError(Integer status, String message, Long timeStamp) {
		super();
		this.status = status;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	/**
	 * Getters and Setters
	 * @return
	 */
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	
}
