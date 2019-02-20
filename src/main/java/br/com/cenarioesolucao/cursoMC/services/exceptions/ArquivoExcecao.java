package br.com.cenarioesolucao.cursoMC.services.exceptions;

public class ArquivoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ArquivoExcecao(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ArquivoExcecao(String message) {
		super(message);
		
	}

	
}
