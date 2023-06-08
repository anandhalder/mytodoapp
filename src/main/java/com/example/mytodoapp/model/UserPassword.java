package com.example.mytodoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
public class UserPassword {

	@Id
	private Long userId;

	private String password;
}
