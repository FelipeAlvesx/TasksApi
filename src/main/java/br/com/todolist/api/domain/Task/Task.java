package br.com.todolist.api.domain.Task;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "Task")
@Table(name = "tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Task {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String desc;
    private Boolean stats;


    public Task(TaskCreateDto taskCreateDto){
        this.name = taskCreateDto.name();
        this.desc = taskCreateDto.desc();
        this.stats = false;
    }


    public void update(TaskUpdateDto taskUpdateDto) {
        if (taskUpdateDto.name() != null){
            this.name = taskUpdateDto.name();
        }
        if (taskUpdateDto.desc() != null){
            this.desc = taskUpdateDto.desc();
        }
        if (taskUpdateDto.stats() != null){
            this.stats = taskUpdateDto.stats();
        }
    }

    public void delete() {
        this.stats = true;
    }
}
