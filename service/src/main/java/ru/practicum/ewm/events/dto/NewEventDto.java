package ru.practicum.ewm.events.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
public class NewEventDto {
    @NotBlank(message = "Краткое описание события должно существовать и не быть пустым")
    @Length(min = 20, max = 2000)
    private String annotation;

    @NotNull
    @Positive(message = "ID категории не может быть отрицательным числом")
    private Long category;

    @NotBlank(message = "Не задано полное описание события")
    @Length(min = 20, max = 7000)
    private String description;

    @NotNull(message = "Не задана дата проведения события")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;

    @Valid
    @NotNull(message = "Не указаны координаты места проведения события")
    private LocationDto location;

    private Boolean paid;

    @PositiveOrZero(message = "Ограничение на количество участников не может быть отрицательным числом")
    private Long participantLimit;

    private Boolean requestModeration;

    @NotBlank(message = "Заголовок события должно существовать и не быть пустым")
    @Length(min = 3, max = 120)
    private String title;

    public boolean hasPaid() {
        return this.paid != null;
    }

    public boolean hasParticipantLimit() {
        return this.participantLimit != null;
    }

    public boolean hasRequestModeration() {
        return this.requestModeration != null;
    }
}
