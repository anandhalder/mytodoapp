package com.example.mytodoapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserPassword {

	@Id
	private Long userId;

	private String password;
}
