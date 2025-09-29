package br.com.todolist.api.domain.Task;

public record TaskUpdateDto(Long id, String name, String desc, Boolean stats) {

    public TaskUpdateDto(Task task){
        this(task.getId(), task.getName(), task.getDesc(), task.getStats());
    }

}
