package com.example.mytodoapp.exceptions;

public class ResourceNotFoundException extends RuntimeException {
		public ResourceNotFoundException(String message) {
				super(message);
		}
}
