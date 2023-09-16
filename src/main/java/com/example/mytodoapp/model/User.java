package com.example.mytodoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class User {

	@Getter
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Getter
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Task> tasks = new ArrayList<>();

	public void setId(Long id) {
		this.id = id;
	}

	public @NotNull String getUsername() {
		return this.username;
	}

	public void setUsername(@NotNull String username) {
		this.username = username;
	}

	public void AddTask(Task task) {
		this.tasks.add(task);
	}

	public void removeTask(Task task) {
		this.tasks.remove(task);
	}

	@Override
	public String toString() {
		return "User{" +
						"id=" + this.id +
						", username='" + this.username + '\'' +
						'}';
	}
}
