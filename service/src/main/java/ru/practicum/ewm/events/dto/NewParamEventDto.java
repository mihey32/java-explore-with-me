package ru.practicum.ewm.events.dto;

import lombok.*;
import ru.practicum.ewm.enums.States;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NewParamEventDto {
    private List<Long> users;
    private List<States> states;
    private List<Long> categories;
    private LocalDateTime rangeStart;
    private LocalDateTime rangeEnd;
    private Integer from;
    private Integer size;
}