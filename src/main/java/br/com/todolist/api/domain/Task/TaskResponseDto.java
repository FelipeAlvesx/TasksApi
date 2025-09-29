package br.com.todolist.api.domain.Task;

public record TaskResponseDto(Long id, String name, String desc, Boolean stats) {

    public TaskResponseDto(Task task){
        this(task.getId(), task.getName(), task.getDesc(), task.getStats());
    }
}
