package com.example.mytodoapp.exceptions;

import com.example.mytodoapp.response.ErrorResponse;
import com.example.mytodoapp.response.ValidationResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {

		ErrorResponse errorResponse = ErrorResponse
						.builder()
						.status(HttpStatus.NOT_FOUND)
						.message(ex.getMessage())
						.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}

	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex) {

		ErrorResponse errorResponse = ErrorResponse
						.builder()
						.status(HttpStatus.CONFLICT)
						.message(ex.getMessage())
						.build();
		return ResponseEntity.status(HttpStatus.CONFLICT).body(errorResponse);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

		List<FieldError> error_list =  ex.getFieldErrors();

		return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body(ValidationResponse
										.builder()
										.message("Validation Error")
										.fieldError(error_list)
										.build());
	}
}
