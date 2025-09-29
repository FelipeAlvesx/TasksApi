package br.com.todolist.api.domain.Task;

import jakarta.validation.constraints.NotBlank;

public record TaskCreateDto(
        @NotBlank
        String name,
        @NotBlank
        String desc
) {
}
