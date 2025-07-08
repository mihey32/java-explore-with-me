package ru.practicum.ewm.compilations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import ru.practicum.ewm.events.dto.EventShortDto;

import java.util.Set;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class CompilationDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    private Set<EventShortDto> events;
    private Boolean pinned;
    private String title;
}
