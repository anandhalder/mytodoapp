package com.example.mytodoapp.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Getter
@Builder
public class SuccessAuthResponse {

	private final String token;
	private final String message;
}
