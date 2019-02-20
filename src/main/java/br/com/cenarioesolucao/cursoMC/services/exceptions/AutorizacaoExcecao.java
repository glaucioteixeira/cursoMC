package br.com.cenarioesolucao.cursoMC.services.exceptions;

public class AutorizacaoExcecao extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorizacaoExcecao(String message, Throwable cause) {
		super(message, cause);
		
	}

	public AutorizacaoExcecao(String message) {
		super(message);
		
	}

	
}
