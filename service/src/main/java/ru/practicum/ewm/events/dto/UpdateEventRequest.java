package ru.practicum.ewm.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import ru.practicum.ewm.locations.LocationDto;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public abstract class UpdateEventRequest {
    @Length(min = 20, max = 2000)
    private String annotation;

    @Positive(message = "ID категории не может быть отрицательным числом")
    private Long category;

    @Length(min = 20, max = 7000)
    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @Valid
    private LocationDto location;

    private Boolean paid;

    @PositiveOrZero(message = "Ограничение на количество участников не может быть отрицательным числом")
    private Long participantLimit;

    private Boolean requestModeration;

    @Length(min = 3, max = 120)
    private String title;

    public boolean hasAnnotation() {
        return this.annotation != null;
    }

    public boolean hasCategory() {
        return this.category != null;
    }

    public boolean hasDescription() {
        return this.description != null;
    }

    public boolean hasEventDate() {
        return this.eventDate != null;
    }

    public boolean hasLocation() {
        return this.location != null;
    }

    public boolean hasPaid() {
        return this.paid != null;
    }

    public boolean hasParticipantLimit() {
        return this.participantLimit != null;
    }

    public boolean hasRequestModeration() {
        return this.requestModeration != null;
    }

    public boolean hasTitle() {
        return this.title != null;
    }
}
