package ru.practicum.ewm.locations;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"lat", "lon"})
public class Location {
    private Float lat;
    private Float lon;
}
