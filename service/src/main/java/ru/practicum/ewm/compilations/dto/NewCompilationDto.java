package ru.practicum.ewm.compilations.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class NewCompilationDto {
    private Set<Long> events;
    private Boolean pinned;
    @NotBlank(message = "Заголовок подборки событий не может отсутствовать или быть пустым")
    @Length(min = 1, max = 50)
    private String title;
}
