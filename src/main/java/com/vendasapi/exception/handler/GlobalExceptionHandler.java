package com.vendasapi.exception.handler;

import com.vendasapi.exception.ErrorResponse;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponse> handleIllegalArgument(IllegalArgumentException ex, WebRequest request) {
		log.warn("Argumento inválido: {}", ex.getMessage());
		return buildResponse(ex, HttpStatus.BAD_REQUEST, "Argumento inválido", request);
	}

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(EntityNotFoundException ex, WebRequest request) {
		log.warn("Recurso não encontrado: {}", ex.getMessage());
		return buildResponse(ex, HttpStatus.NOT_FOUND, "Recurso não encontrado", request);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, WebRequest request) {
		List<String> details = ex.getBindingResult()
				.getFieldErrors()
				.stream()
				.map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());

		ErrorResponse error = ErrorResponse.builder()
				.message("Dados inválidos")
				.error("Erro de validação")
				.status(HttpStatus.BAD_REQUEST.value())
				.timestamp(LocalDateTime.now())
				.path(request.getDescription(false).replace("uri=", ""))
				.details(details)
				.build();

		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, WebRequest request) {
		log.error("Erro inesperado: ", ex);
		return buildResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno", request);
	}

	private ResponseEntity<ErrorResponse> buildResponse(Exception ex, HttpStatus status, String error, WebRequest request) {
		ErrorResponse body = ErrorResponse.of(
				ex.getMessage(),
				status.value(),
				error,
				request.getDescription(false).replace("uri=", "")
		);
		return ResponseEntity.status(status).body(body);
	}
}
