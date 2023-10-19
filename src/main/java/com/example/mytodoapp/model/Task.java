package com.example.mytodoapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tasks")
public class Task {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private boolean completed;

	@Column(nullable = true)
	private LocalDate dueDate = LocalDate.now().plusDays(7);

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@Override
	public String toString() {
		return "Task{" +
						"id=" + id +
						", description='" + description + '\'' +
						", completed=" + completed +
						", dueDate=" + dueDate +
						'}';
	}
}
