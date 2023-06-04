package com.example.mytodoapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "blacklist_token")
public class BlacklistToken {

	@Id
	private String token;

	@Column(nullable = false)
	private LocalDateTime expirationDate;
}
