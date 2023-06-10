package com.example.mytodoapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class ValidationResponse {

	private  String message;
	private List<FieldError> fieldError;
}
