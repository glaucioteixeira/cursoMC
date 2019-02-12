package br.com.cenarioesolucao.cursoMC.services.exceptions;

public class ViolacaoIntegridadeDadoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ViolacaoIntegridadeDadoExcecao(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ViolacaoIntegridadeDadoExcecao(String message) {
		super(message);
		
	}

	
}
