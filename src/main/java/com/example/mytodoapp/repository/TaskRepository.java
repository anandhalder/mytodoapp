package com.example.mytodoapp.repository;

import com.example.mytodoapp.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

	Page<Task> findByUserId(Long userId, Pageable pageable);

	Optional<Task> findByIdAndUserId(Long id, Long userId);

	List<Task> findAllByUserId(Long userId);

	@Modifying
	@Query("DELETE FROM Task t WHERE t.id = :taskId AND t.user.id = :userId")
	int deleteByIdAndUserId(@Param("taskId") Long taskId, @Param("userId") Long userId);
}
