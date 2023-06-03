package com.example.mytodoapp.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class ErrorResponse {

	private HttpStatus status;
	private String message;
}
