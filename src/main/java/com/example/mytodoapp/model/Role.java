package com.example.mytodoapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

	@ManyToMany
	@JoinTable(
					name = "user_roles",
					joinColumns = @JoinColumn(name = "role_id"),
					inverseJoinColumns = @JoinColumn(name = "user_id"))
	Set<User> users = new HashSet<>();

	@Id
	@GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private String name;
}
