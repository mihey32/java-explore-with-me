package ru.practicum.ewm.locations;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode(of = {"lat", "lon"})
@NoArgsConstructor
@AllArgsConstructor
public class LocationDto {
    @NotNull(message = "Широта должна быть указана")
    private Float lat;
    @NotNull(message = "Долгота должна быть указана")
    private Float lon;
}
