package com.example.mytodoapp.Utils;

public record Pair(Object key, Object value) {
	@Override
	public String toString() {
		return "Pair{" +
						"key='" + key + '\'' +
						", value='" + value + '\'' +
						'}';
	}
}
