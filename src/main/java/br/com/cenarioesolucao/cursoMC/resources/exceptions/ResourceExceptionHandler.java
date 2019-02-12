package br.com.cenarioesolucao.cursoMC.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoExcecao.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoExcecao e,HttpServletRequest request) {
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError body = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(status).body(body);
		
	}
}
