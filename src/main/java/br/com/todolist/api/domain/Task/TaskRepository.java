package br.com.todolist.api.domain.Task;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Page<Task>findAllByStatsFalse(Pageable pageable);

    Page<Task>findAllByStatsTrue(Pageable pageable);
}
