package com.example.mytodoapp.Utils;

import org.springframework.stereotype.Service;

@Service
public class CommonUtils {

	public String sanitize(String input) {
		// Define a regular expression pattern to allow only alphanumeric characters and spaces
		String sanitized = input.replaceAll("[^a-zA-Z0-9\\s]", "");
		return sanitized.trim();
	}
}
