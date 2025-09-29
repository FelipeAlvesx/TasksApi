package br.com.todolist.api.controller;

import br.com.todolist.api.domain.Task.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("task")
public class TaskController {

        @Autowired
        private TaskRepository taskRepository;

        @PostMapping
        @Transactional
        public ResponseEntity crateTask(@RequestBody @Valid TaskCreateDto taskCreateDto, UriComponentsBuilder uriComponentsBuilder){
            var task = new Task(taskCreateDto);
            taskRepository.save(task);

            var uri = uriComponentsBuilder.path("/task/{id}").buildAndExpand(task.getId()).toUri();

            return ResponseEntity.created(uri).body(new TaskResponseDto(task));
        }

        @GetMapping
        public ResponseEntity<Page<TaskResponseDto>> readTask(@PageableDefault(sort = {"name"}, size = 10)Pageable pageable){
            var pageResponse = taskRepository.findAll(pageable).map(TaskResponseDto::new);

            return ResponseEntity.ok(pageResponse);
        }

        @PutMapping
        @Transactional
        public ResponseEntity updateTask(@RequestBody TaskUpdateDto taskUpdateDto){
            var task = taskRepository.getReferenceById(taskUpdateDto.id());
            task.update(taskUpdateDto);


            return ResponseEntity.ok(new TaskUpdateDto(task));
        }


        @DeleteMapping("/{id}")
        @Transactional
        public ResponseEntity deleteTask(@PathVariable Long id){
            var task = taskRepository.getReferenceById(id);
            taskRepository.delete(task);

            return ResponseEntity.noContent().build();
        }

        @GetMapping("/completed")
        public ResponseEntity<Page<TaskResponseDto>> readCompletedTasks(@PageableDefault(sort = {"name"}, size = 10) Pageable pageable){
            var pageResponse = taskRepository.findAllByStatsTrue(pageable).map(TaskResponseDto::new);

            return ResponseEntity.ok(pageResponse);

        }

        @GetMapping("/not-completed")
        public ResponseEntity<Page<TaskResponseDto>> readNotCompleted(@PageableDefault(sort = {"name"}, size = 10) Pageable pageable){
            var pageResponse = taskRepository.findAllByStatsFalse(pageable).map(TaskResponseDto::new);

            return ResponseEntity.ok(pageResponse);
        }
}
