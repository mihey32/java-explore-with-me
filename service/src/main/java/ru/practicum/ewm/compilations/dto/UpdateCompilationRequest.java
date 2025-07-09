package ru.practicum.ewm.compilations.dto;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCompilationRequest {
    private Set<Long> events;

    private Boolean pinned;

    @Length(min = 1, max = 50)
    private String title;

    public boolean hasEvents() {
        return this.pinned != null;
    }

    public boolean hasPinned() {
        return this.pinned != null;
    }

    public boolean hasTitle() {
        return this.title != null;
    }
}
