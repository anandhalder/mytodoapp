package com.example.mytodoapp.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Builder
public class SuccessResponse<T> {

	private HttpStatus status;
	private String message;
	private T data;
}
