package com.example.mytodoapp.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SuccessResponse<T> {

	private String message;
	private T data;
}
