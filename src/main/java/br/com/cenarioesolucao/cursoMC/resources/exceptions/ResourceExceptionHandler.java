package br.com.cenarioesolucao.cursoMC.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ViolacaoIntegridadeDadoExcecao;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoExcecao.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		StandardError body = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(ViolacaoIntegridadeDadoExcecao.class)
	public ResponseEntity<StandardError> violacaoIntegridadeDadoExcecao(ViolacaoIntegridadeDadoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		StandardError body = new StandardError(status.value(), e.getMessage(), System.currentTimeMillis());
		
		return ResponseEntity.status(status).body(body);
	}
	
	// Exceções lançadas em função da validação do Bean Validation 
	@ExceptionHandler(MethodArgumentNotValidException.class) // Essa exceção pertence ao framework
	public ResponseEntity<StandardError> argumentoMetodoNaoValidoExcecao(MethodArgumentNotValidException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		// ValidationError body = new ValidationError(status.value(), e.getMessage(), System.currentTimeMillis());
		ValidationError body = new ValidationError(status.value(), "Error de validação!", System.currentTimeMillis());
		
		/*
		 * Percorre a lista de erros gerados pela exceção e para cada error será gerado o objeto FieldMessage
		 */
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			body.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(body);
	}
	
}
