package br.com.cenarioesolucao.cursoMC.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.model.AmazonS3Exception;

import br.com.cenarioesolucao.cursoMC.services.exceptions.ArquivoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.AutorizacaoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ObjetoNaoEncontradoExcecao;
import br.com.cenarioesolucao.cursoMC.services.exceptions.ViolacaoIntegridadeDadoExcecao;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoExcecao.class)
	public ResponseEntity<StandardError> objetoNaoEncontrado(ObjetoNaoEncontradoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		String error = "Não Encontrado!";
				
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(ViolacaoIntegridadeDadoExcecao.class)
	public ResponseEntity<StandardError> violacaoIntegridadeDadoExcecao(ViolacaoIntegridadeDadoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Integridade de Dados!";
				
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	
	// Exceções lançadas em função da validação do Bean Validation 
	@ExceptionHandler(MethodArgumentNotValidException.class) // Essa exceção pertence ao framework
	public ResponseEntity<StandardError> argumentoMetodoNaoValidoExcecao(MethodArgumentNotValidException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
		String error = "Erro de Validação!";
		
		// ValidationError body = new ValidationError(status.value(), e.getMessage(), System.currentTimeMillis());
		ValidationError body = new ValidationError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		/*
		 * Percorre a lista de erros gerados pela exceção e para cada error será gerado o objeto FieldMessage
		 */
		for (FieldError x : e.getBindingResult().getFieldErrors()) {
			body.addError(x.getField(), x.getDefaultMessage());
		}
		
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(AutorizacaoExcecao.class)
	public ResponseEntity<StandardError> autorizacao(AutorizacaoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.FORBIDDEN;
		String error = "Acesso Negado!";
		
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	
	@ExceptionHandler(ArquivoExcecao.class)
	public ResponseEntity<StandardError> arquivo(ArquivoExcecao e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro de Arquivo!";
		
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	@ExceptionHandler(AmazonServiceException.class)
	public ResponseEntity<StandardError> amazonServiceException(AmazonServiceException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.valueOf(e.getErrorCode());
		String error = "Erro Amazon Service!";
		
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	@ExceptionHandler(AmazonClientException.class)
	public ResponseEntity<StandardError> amazonClientException(AmazonClientException e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro Amazon Client!";
		
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	@ExceptionHandler(AmazonS3Exception.class)
	public ResponseEntity<StandardError> amazonS3Exception(AmazonS3Exception e,HttpServletRequest request) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String error = "Erro Amazon S3!";
		
		StandardError body = new StandardError(System.currentTimeMillis(), status.value(), error, e.getMessage(), request.getRequestURI());
		
		return ResponseEntity.status(status).body(body);
	}
	
	
}
