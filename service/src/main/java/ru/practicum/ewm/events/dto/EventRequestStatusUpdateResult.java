package ru.practicum.ewm.events.dto;

import lombok.*;
import ru.practicum.ewm.requests.dto.ParticipationRequestDto;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventRequestStatusUpdateResult {
    private List<ParticipationRequestDto> confirmedRequests;
    private List<ParticipationRequestDto> rejectedRequests;
}